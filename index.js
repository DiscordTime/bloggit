const {app, BrowserWindow} = require('electron')
const REPO_PATH = 'git@github.com:DiscordTime/bloggit-mds.git';
const LOCAL_PATH = 'lhebas'; // Define the local directory
const gitP = require('simple-git/promise');
const gitS = require('simple-git');
const git = gitP();

let mainWindow

function list_files() {
//    gitP(REPO_PATH).raw(['ls-files'])
//       .then(response => console.log('Raw data from git command: ', response))
//       .catch(error => console.error(error));
    gitS(LOCAL_PATH).raw(['ls-files'], (err, result) => {
	if(err) {
	    console.log(err)
	    return
	}

	console.log('result', result)

    })
}

function clone() {
    console.log('index clone called')

    console.log('git: clone called')
	git.clone(REPO_PATH, LOCAL_PATH)
	.then(result => {
		console.log('result: ', result)
		})
    .catch(err => {
	    console.log('error', err)
	    })
}


list_files()
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

