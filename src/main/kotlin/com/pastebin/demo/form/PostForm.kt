package com.pastebin.demo.form

import com.pastebin.demo.domain.User
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

class PostForm(
//           @NotNull
//           @NotEmpty
//           @Size(min = 1, max = 65000)
           @Lob
           var text : String="",

           @CreationTimestamp
           private var creationTime: Date=Date()
)