import React, { Component } from 'react';
import './Homepage.css';
import Post from '../Post'
import TextsRepository from '../../TextsRepository'

let textsRepository = new TextsRepository();

class Homepage extends Component {
    constructor() {
	super();
	this.state = {
	    posts: []
	}
    }

    async componentDidMount() {
	let _posts = await textsRepository.getTexts(); 
	console.log(_posts);
	this.setState({ posts: _posts });
    }

    render() {
	let { posts } = this.state;
	return( 
	    <div className='Homepage'>
		{posts.map((post, i) => <Post marked_text={post} key={i} />)}
	    </div>
	);	
    }
}

export default Homepage;
