const { app, BrowserWindow } = require('electron')

let mainWindow

function createWindow() {
	mainWindow = new BrowserWindow({
		width: 800,
		height: 600,
		title: 'Bloggit!'
	})
}

app.on('ready', createWindow)
