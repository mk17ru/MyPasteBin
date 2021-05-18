package com.pastebin.demo.controllers

import com.pastebin.demo.domain.Post
import com.pastebin.demo.form.PostForm
import com.pastebin.demo.services.PostService
import com.pastebin.demo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.servlet.http.HttpSession

@Controller
class PostController(@Autowired private var postService: PostService, @Autowired private var userService: UserService) {
    @PostMapping("/writePost")
    fun writePost(model: Model, @ModelAttribute("postForm") postForm : PostForm, httpSession: HttpSession) : String {
        val hash = hashKey()
        val preUser = httpSession.getAttribute("userId")
        val user = if (preUser != null) userService.findById(preUser.toString().toLong()).orElse(null) else null
        val post = Post(
            id = 0,
            user =user,
            hash =hash,
            text =postForm.text,
            ancestorPostId = 0,
            isPublic = true
        )
        val newPost = postService.save(post)
        newPost.ancestorPostId = newPost.id
        postService.save(post)
        postForm.id = newPost.id
        return "redirect:/post/$hash";
    }

    @PostMapping("/changePost/{hash}")
    fun changePost(model: Model, @ModelAttribute("postForm") postForm : PostForm,
                   @PathVariable(value="hash") ancestorHash : String,
                  @ModelAttribute("changeForm") changeForm : PostForm,
                  httpSession: HttpSession) : String {
        val hash = hashKey()
        val preUser = httpSession.getAttribute("userId")
        val user = if (preUser != null) userService.findById(preUser.toString().toLong()).orElse(null) else null
        val ancestorPostIdFirst = postService.findByHash(ancestorHash).orElse(null)
        if (ancestorPostIdFirst == null) {
            System.err.println("error");
            return "redirect:/"
        }
        val ancestorPostId = postService.findById(ancestorPostIdFirst.ancestorPostId).orElse(null)
        if (ancestorPostId == null) {
            System.err.println("No initial post!")
            return "redirect:/"
        }
        val post = Post(
            id = 0,
            user =user,
            hash =hash,
            text =postForm.text,
            ancestorPostId = ancestorPostId.id
        )
        postService.save(post)
        return "redirect:/post/$hash";
    }

    @GetMapping("/post/{hash}")
    fun getPost(model: Model, @PathVariable(value="hash") hash : String, httpSession: HttpSession) : String {
        val post = postService.findByHash(hash).orElse(null)
        if (!post.isPublic) {
            val userStr = httpSession.getAttribute("userId")
            if (userStr == null) {
                return "redirect:/";
            }
            val userId = userStr.toString().toLong()
            val user = userService.findById(userId).orElse(null);
            if (user == null) {
                return "redirect:/";
            }
        }
        if (post == null) {
            System.err.println("No post!")
            return "redirect:/"
        } else {
            model.addAttribute("postForm", PostForm(post))
            model.addAttribute("hash", hash)
            model.addAttribute("changeForm", PostForm(post))
            return "PostPage";
        }
    }

    @GetMapping("/posts")
    fun getPost(model: Model, httpSession: HttpSession) : String {
        val userStr = httpSession.getAttribute("userId")
        if (userStr == null) {
            return "redirect:/";
        }
        val userId = userStr.toString().toLong()
        val user = userService.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/";
        } else {
            val posts : List<Post> = postService.findPostByUser(user);
            model.addAttribute("postMap", posts.stream().collect(Collectors.groupingBy { it.ancestorPostId }))
            return "PostsPage";
        }
    }

    @Transactional
    @DeleteMapping("/post/{hash}")
    fun deletePost(model: Model, httpSession: HttpSession, @PathVariable(value="hash") hash : String) : String {
        postService.deletePostByHash(hash);
        val userStr = httpSession.getAttribute("userId")
        if (userStr == null) {
            return "redirect:/";
        }
        val userId = userStr.toString().toLong()
        val user = userService.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/";
        } else {
            return "redirect:/posts";
        }
    }

    @Transactional
    @PatchMapping("/post/visible/{hash}")
    fun patchPost(model: Model, httpSession: HttpSession, @PathVariable(value="hash") hash : String) : String {
        val post = postService.findByHash(hash).orElse(null);
        if (post == null) {
            return "redirect:/";
        }
        post.isPublic = !post.isPublic;
        postService.save(post)
        return "redirect:/";
    }




    fun hashKey(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..16)
            .map { allowedChars.random() }
            .joinToString("")
    }
}