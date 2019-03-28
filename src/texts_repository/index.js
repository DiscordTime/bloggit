class TextsRepository {
  //Temporary implementation.
  getTexts() {
    return new Promise((resolve, reject) => {
      setTimeout(function() { //Start the timer
        resolve(['# text1', '## text2', '### text3'])
      }.bind(this), 1000)
    })
  }
}

export default TextsRepository
