package com.pastebin.demo.form

import com.pastebin.demo.domain.User
import javax.validation.constraints.*

class UserForm(
    @NotEmpty(message="empty string")
    @Size(min = 2, max = 30)
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