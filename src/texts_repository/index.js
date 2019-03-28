const { ipcRenderer } = window.require('electron')
const ACTION_LIST_MD_FILES = 'list-md-files'
const ACTION_LIST_MD_FILES_DONE = 'list-md-files-done'

function throwIfIsNotRendererProcess() {
    if (window.process && window.process.type !== 'renderer') {
        throwException('Function cannot be called outside the Renderer process!')
    }
}

function throwException(message) {
    throw { name: "Exception", message: message }
}

class TextsRepository {
  getTexts() {
    return new Promise((resolve, reject) => {
      throwIfIsNotRendererProcess()

      ipcRenderer.send(ACTION_LIST_MD_FILES)

      const onDoneListFiles = (event, files) => {
        ipcRenderer.removeAllListeners(ACTION_LIST_MD_FILES_DONE)
        resolve(files)
      }

      ipcRenderer.on(ACTION_LIST_MD_FILES_DONE, onDoneListFiles)
    })
  }
}

export default TextsRepository
