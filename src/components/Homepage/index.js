import React, { Component } from 'react';
import './Homepage.css';
import Post from '../Post'
import TextsRepository from '../../TextsRepository'

let textsRepository = new TextsRepository();

class Homepage extends Component {
  constructor() {
    super();
    this.state = {
      posts: [],
      loading: true
    }
  }

  async componentDidMount() {
      let _posts = await textsRepository.getTexts();
      console.log(_posts);
      this.setState({
        posts: _posts,
        loading: false
      });
  }

  render() {
    let data;
    if (this.state.loading) {
      data = <img  src={ require('../../images/loading.gif') } />
    } else {
        let { posts } = this.state;
        data = posts.map((post, i) => <Post marked_text={post} key={i} />)
    }
    return(
      <div className='Homepage'>
        {data}
      </div>
    );
  }
}

export default Homepage;
