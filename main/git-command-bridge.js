const { ipcMain } = require('electron')
const fs = require('fs')
const REPO_PATH = 'git@github.com:DiscordTime/bloggit-mds.git';
const LOCAL_PATH = 'lhebas'; // Define the local directory
const gitP = require('simple-git/promise');
const git = gitP(LOCAL_PATH);
const ACTION_LIST_MD_FILES = 'list-md-files'
const ACTION_LIST_MD_FILES_DONE = 'list-md-files-done'

function throwIfIsNotMainProcess() {
    if (process && process.type !== 'browser') {
        throwException('Function cannot be called outside the Main process!')
    }
}

function throwException(message) {
    throw { name: "Exception", message: message }
}

class GitCommandBridge {

  async listFiles() {
    return git.raw(['ls-files'])
  }

  async logFile(file) {
    return git.silent(true).log({'file': file})
  }

  async showFile(file) {
    return git.silent(true).show('HEAD:' + file)
  }

  async cloneOrPull() {
    if (!fs.existsSync(LOCAL_PATH)){
      fs.mkdirSync(LOCAL_PATH)
      return git.clone(REPO_PATH);
    } else {
      return git.pull()
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

  async listMdFiles(event) {

    try {
      await this.cloneOrPull()
      let files = await this.listFiles()
      let filesPromises = files.split("\n")
        .filter(value => value != '')
        .map(value => this.mapFilePathToFullObject(value))

      let results = await Promise.all(filesPromises)
      results.forEach(i => console.log(i))
      event.sender.send(ACTION_LIST_MD_FILES_DONE, results)

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
