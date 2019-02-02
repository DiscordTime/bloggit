import React, { Component } from 'react';
import './Homepage.css';
import Post from '../Post'

class Homepage extends Component {
    render() {
	var posts = ["# text1","text2","text3"];
	return( 
	    <div className="Homepage">
		{posts.map((post, i) => <Post marked_text={post} key={i} />)}
	    </div>
	);	
    }
}

export default Homepage;
