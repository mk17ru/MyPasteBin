package com.pastebin.demo.form

import com.pastebin.demo.domain.Post
import com.pastebin.demo.domain.User
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class PostForm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =0,

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    var text: String = "",

    @CreationTimestamp
    private var creationTime: Date = Date()
) {
    constructor(post: Post) : this(post.id, post.text)
}