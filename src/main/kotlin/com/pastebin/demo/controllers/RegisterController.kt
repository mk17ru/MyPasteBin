package com.pastebin.demo.controllers

import com.pastebin.demo.domain.User
import com.pastebin.demo.form.UserForm
import com.pastebin.demo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession

@Controller
class RegisterController(@Autowired private var userService: UserService) {
    @GetMapping( "/register")
    fun index(model: Model): String {
        model.addAttribute("registerForm", UserForm());
        return "RegisterPage"
    }


    //TODO Valid
    @PostMapping("/register")
    fun enter(@ModelAttribute("registerForm") userForm : UserForm, httpSession : HttpSession): String {
        // TODO valid for user already exists
        val user = userService.findByLoginAndPassword(userForm.login, userForm.password)
        if (user == null) {
            userService.save(userForm.toUser())
            httpSession.setAttribute("userId",
                userService.findByLoginAndPassword(userForm.login, userForm.password)?.id);
            return "redirect:/";
        } else {
            return "RegisterPage";
        }
    }
}