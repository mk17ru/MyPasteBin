package com.pastebin.demo.repositories

import com.pastebin.demo.domain.Post
import com.pastebin.demo.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface PostRepository : JpaRepository<Post, Long> {
    override fun findById(id : Long) : Optional<Post>
    fun save(post : Post) : Post
    fun findByHash(hash: String): Optional<Post>
    fun findPostByUser(user: User) : List<Post>
    fun deletePostByHash(hash: String)
}