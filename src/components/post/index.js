import React, { Component } from 'react';
import './Post.css';
import ReactMarkdown from 'react-markdown';
import Code from './code'

class Post extends Component {

  transformImageUri(uri) {
    // TODO: fix symlink
    console.log(uri);
    // // let newUri = '../../res/repo/' + uri
    // return require(newUri)
  }

  render() {
    let date = new Date(this.props.date)
    return (
      <article className="post">
        <section>
        <div className="post_content">
          <ReactMarkdown
            source={this.props.markdown}
            /*transformImageUri={this.transformImageUri}*/
            escapeHtml={false}
            renderers={{code: Code}} />
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
