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

  async componentDidMount() {
      let _posts = await postRepository.getPosts()
      console.log(_posts);
      this.setState({
        posts: _posts,
        loading: false
      });
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
