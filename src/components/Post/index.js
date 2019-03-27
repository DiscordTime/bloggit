import React, { Component } from 'react';
import './Post.css';
import ReactMarkdown from 'react-markdown';

class Post extends Component {
  render() {
    return (
      <div className="Post">
      <ReactMarkdown source={this.props.marked_text} />
      </div>
    );
  }
}

export default Post;
