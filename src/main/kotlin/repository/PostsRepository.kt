package repository

import data.Post

interface PostsRepository : Repository<Post>, AutoCloseable