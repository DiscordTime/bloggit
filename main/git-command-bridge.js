const { ipcMain } = require('electron')
const fs = require('fs')
const REPO_PATH = 'git@github.com:DiscordTime/bloggit-mds.git';
const LOCAL_PATH = 'repo'; // Define the local directory
const gitP = require('simple-git/promise');
const ACTION_LIST_MD_FILES = 'list-md-files'
const ACTION_LIST_MD_FILES_DONE = 'list-md-files-done'
const PAGE_SIZE = 10

function throwIfIsNotMainProcess() {
    if (process && process.type !== 'browser') {
        throwException('Function cannot be called outside the Main process!')
    }
}

function throwException(message) {
    throw { name: "Exception", message: message }
}

String.prototype.splitGit = function(){
  return this.split('\n').filter(value => value != '')
}

class GitCommandBridge {

  constructor(){
    if (!fs.existsSync(LOCAL_PATH)){
      fs.mkdirSync(LOCAL_PATH)
      fs.symlinkSync('../../' + LOCAL_PATH, 'src/res/repo')
      this._shouldClone = true
    }
    this.setGitDir(LOCAL_PATH)
    this._commitPointer = 'HEAD'
  }

  setGitDir(dir) {
    this.git = gitP(dir)
  }

  async listNextCommitsWithMarkdownFiles(commitID) {
    if (commitID != 'HEAD') {
      commitID += '~1'
    }
    return this.git.raw(['rev-list',commitID,'--','\*md'])
  }

  async listMarkdownFilesOfCommit(commitID) {
    return this.git.silent(true).show(['--pretty=', '--name-only', commitID,'--','\*md'])
  }

  async listFiles() {
    return this.git.raw(['ls-files'])
  }

  async logFile(file) {
    return this.git.silent(true).log({'file': file})
  }

  async showFile(file) {
    return this.git.silent(true).show('HEAD:' + file)
  }

  async cloneOrPull() {
    if (this._shouldClone){
      this._shouldClone = false
      return this.git.clone(REPO_PATH, ".");
    } else {
      return this.git.pull()
    }
  }

  mapFilePathToFullObject(filePath) {
    return Promise.all([
          this.showFile(filePath),
          this.logFile(filePath)
        ])
        .then(values => {
          return Object.assign(
            {markdown: values[0]},
            values[1].latest)
        })
  }

  mapCommitToFiles(commit) {
    return this.listMarkdownFilesOfCommit(commit)
  }

  async listMdFiles(event) {

    try {
      await this.cloneOrPull()

      let commits = await this.listNextCommitsWithMarkdownFiles(this._commitPointer)
      commits = commits.splitGit()
      for (let i = 0; i < Math.min(commits.length,PAGE_SIZE); i++) {
        let commit = commits[i]
        let files = await this.mapCommitToFiles(commit)
        files = files.splitGit()
        for (const file of files) {
          let object = await this.mapFilePathToFullObject(file)
          event.sender.send(ACTION_LIST_MD_FILES_DONE, object)
        }
        //this._commitPointer = commit
      }

    } catch (e) {
      console.log(e);
    }
  }

  register() {
    throwIfIsNotMainProcess()
    ipcMain.on(ACTION_LIST_MD_FILES, this.listMdFiles.bind(this))
  }

  init() {
    this.register()
  }
}

module.exports = {
  GitCommandBridge: GitCommandBridge
}
