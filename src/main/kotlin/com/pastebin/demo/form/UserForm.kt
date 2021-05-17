package com.pastebin.demo.form

import com.pastebin.demo.domain.User
import org.springframework.context.annotation.Bean
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UserForm(
    @NotNull
    @NotEmpty
    var login: String="",
    @NotNull
    @NotEmpty
    var firstname : String="",
    @NotNull
    @NotEmpty
    var lastname: String="",
    @NotNull
    @NotEmpty
    var password : String="") {

    fun toUser(): User {
        return User(0, this.login, this.firstname, this.lastname, this.password, listOf())
    }
}