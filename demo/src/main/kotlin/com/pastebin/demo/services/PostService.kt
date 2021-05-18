package com.pastebin.demo.services

import com.pastebin.demo.domain.Post
import com.pastebin.demo.domain.User
import com.pastebin.demo.repositories.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(@Autowired private var postRepository: PostRepository) {

    fun findById(id : Long) : Optional<Post> {
        return postRepository.findById(id)
    }

    fun findByHash(hash : String) : Optional<Post> {
        return postRepository.findByHash(hash)
    }

    fun save(post : Post) : Post {
        return postRepository.save(post)
    }

    fun findPostByUser(user: User) : List<Post> {
        return postRepository.findPostByUser(user);
    }

    fun deletePostByHash(hash: String)  {
        return postRepository.deletePostByHash(hash);
    }


}