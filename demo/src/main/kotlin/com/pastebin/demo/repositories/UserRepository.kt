package com.pastebin.demo.repositories

import com.pastebin.demo.domain.Post
import com.pastebin.demo.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String): User?
    override fun findById(id: Long): Optional<User>
    fun save(user: User)
    fun findByLoginAndPassword(login: String, password: String) : User?

}