package com.pastebin.demo.controllers;

import com.pastebin.demo.form.PostForm
import com.pastebin.demo.form.UserForm
import com.pastebin.demo.repositories.PostRepository
import com.pastebin.demo.services.PostService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class IndexController {

    @GetMapping( "", "/")
    fun index(model: Model): String {
        model.addAttribute("postForm", PostForm());
        return "IndexPage"
    }

    @GetMapping("/logout")
    fun logout(): String {
        return "redirect:/";
    }


}
