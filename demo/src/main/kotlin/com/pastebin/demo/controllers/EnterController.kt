package com.pastebin.demo.controllers;

import com.pastebin.demo.domain.User
import com.pastebin.demo.form.UserForm
import com.pastebin.demo.repositories.PostRepository
import com.pastebin.demo.services.PostService
import com.pastebin.demo.services.UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.net.http.HttpClient
import javax.servlet.http.HttpSession

@Controller
class EnterController (@Autowired private var userService: UserService) {

    @GetMapping( "/enter")
    fun index(model: Model): String {
        model.addAttribute("enterForm", UserForm());
        return "EnterPage"
    }


    //TODO Valid
    @PostMapping("/enter")
    fun enter(@ModelAttribute("enterForm") userForm : UserForm, httpSession : HttpSession): String {
        val user = userService.findByLoginAndPassword(userForm.login, userForm.password)
        if (user != null) {
            httpSession.setAttribute("userId", user.id);
            return "redirect:/";
        } else {
            return "EnterPage";
        }
    }


}