import React, { Component } from 'react';
import './Post.css';
import ReactMarkdown from 'react-markdown';

class Post extends Component {
  render() {
    let date = new Date(this.props.date)
    return (
      <article className="post">
        <section>
        <div className="post_content">
          <ReactMarkdown source={this.props.markdown} />
        </div>
        <blockquote>
          <address>{this.props.author_name}</address>
          <time>{date.toLocaleDateString()}</time>
        </blockquote>
        </section>
      </article>
    );
  }
}

export default Post;
