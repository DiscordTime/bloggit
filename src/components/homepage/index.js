import React, { Component } from 'react';
import './Homepage.css';
import Post from '../post'
import PostRepository from '../../post_repository'

let postRepository = new PostRepository()

class Homepage extends Component {
  constructor() {
    super();
    this.state = {
      posts: [],
      loading: true
    }
  }

  addNewPost(post) {
    this.setState(prevState => ({
      posts: [...prevState.posts, post],
      loading: false
    }))
  }

  async componentDidMount() {
      postRepository.bindNewPostListener(this.addNewPost.bind(this))
      postRepository.fetch()
  }

  render() {
    let data;
    if (this.state.loading) {
      data = <img  src={ require('../../res/images/loading.gif') } />
    } else {
        let { posts } = this.state;
        data = posts.map((post, i) => <Post {...post} key={i} />)
    }
    return(
      <div className='Homepage'>
        {data}
      </div>
    );
  }
}

export default Homepage;
