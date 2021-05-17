package com.pastebin.demo.repositories

import com.pastebin.demo.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post, Long> {
    override fun findById(id : Long) : Optional<Post>
    fun save(post : Post)
    fun findByHash(hash: String): Optional<Post>
}