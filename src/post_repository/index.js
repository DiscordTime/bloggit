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

class PostRepository {

  bindNewPostListener(addNewPost) {
    this._addNewPost = (event, post) => {
      addNewPost(post)
    }
  }

  fetch() {
    throwIfIsNotRendererProcess()
    ipcRenderer.send(ACTION_LIST_MD_FILES)
    ipcRenderer.on(ACTION_LIST_MD_FILES_DONE, this._addNewPost)
  }
}

export default PostRepository
