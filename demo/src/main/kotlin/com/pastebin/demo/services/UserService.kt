package com.pastebin.demo.services

import com.pastebin.demo.domain.User
import com.pastebin.demo.repositories.PostRepository
import com.pastebin.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(@Autowired private var userRepository: UserRepository) {

    fun save(user : User) {
        userRepository.save(user);
    }

    fun findByLogin(login : String) : User? {
        return userRepository.findByLogin(login)
    }

    fun findByLoginAndPassword(login: String, password : String) : User? {
        return userRepository.findByLoginAndPassword(login, password);
    }

    fun findById(id: Long): Optional<User> {
        return userRepository.findById(id);
    }

    fun isLoginVacant(login: String): Boolean {
        return userRepository.findByLogin(login) == null
    }


}