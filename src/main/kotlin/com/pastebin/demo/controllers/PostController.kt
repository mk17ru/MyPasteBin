package com.pastebin.demo.controllers

import com.pastebin.demo.domain.Post
import com.pastebin.demo.form.PostForm
import com.pastebin.demo.services.PostService
import com.pastebin.demo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession

@Controller
class PostController(@Autowired private var postService: PostService, @Autowired private var userService: UserService) {
    @PostMapping("/writePost")
    fun writePost(@ModelAttribute("postForm") postForm : PostForm, httpSession: HttpSession) : String {
        val hash = hashKey()
        val preUser = httpSession.getAttribute("userId")
        val user = if (preUser != null) userService.findById(preUser.toString().toLong()).orElse(null) else null
        val post = Post(
            id = 0,
            user=user,
            hash=hash,
            text=postForm.text
        )
        postService.save(post)
        return "redirect:/posts/$hash";
    }

    @GetMapping("/posts/{hash}")
    fun getPosts(model: Model, @PathVariable(value="hash") hash : String) : String {
        val post = postService.findByHash(hash).orElse(null)
        if (post == null) {
            System.err.println("No post!")
            return "redirect:/"
        } else {
            model.addAttribute("postForm", PostForm(post.text))
            return "PostPage";
        }
    }




    fun hashKey(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..16)
            .map { allowedChars.random() }
            .joinToString("")
    }
}