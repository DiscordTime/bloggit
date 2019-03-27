const {app, BrowserWindow} = require('electron')
const fs = require('fs')
const REPO_PATH = 'git@github.com:DiscordTime/bloggit-mds.git';
const LOCAL_PATH = 'lhebas'; // Define the local directory
const gitP = require('simple-git/promise');
const gitS = require('simple-git');
const git = gitP(LOCAL_PATH);

let mainWindow

async function listFiles() {
  return git.raw(['ls-files'])
}

async function showFile(file) {
  if (file != '') {
    let content = await git.silent(true).show('HEAD:' + file)
    console.log(content);
  }
}

async function cloneOrPull() {
  if (!fs.existsSync(LOCAL_PATH)){
    console.log('LOCAL_PATH doesn\'t exist')
    console.log('Creating folder');
    fs.mkdirSync(LOCAL_PATH)
    console.log('Calling git clone')
    return git.clone(REPO_PATH);
  } else {
    console.log('Calling git pull')
    return git.pull()
  }
}

async function init() {
  try {
    await cloneOrPull()
    let files = await listFiles()
    files.split("\n").forEach(i => showFile(i));
  } catch (e) {
    console.log(e);
  }
}

function createWindow () {
  mainWindow = new BrowserWindow({width: 800, height: 600})
  mainWindow.loadURL('http://localhost:3000')

  mainWindow.on('closed', function () {
    mainWindow = null
  })
}

app.on('ready', createWindow)

app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', function () {
  if (mainWindow === null) {
    createWindow()
  }
})

init()
