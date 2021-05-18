package com.pastebin.demo.domain

import com.pastebin.demo.form.UserForm
import javax.persistence.*
import javax.validation.constraints.*

@Entity
class User(@Id
           @GeneratedValue(strategy=GenerationType.IDENTITY)
           var id: Long,

           @NotNull
           @NotEmpty
           var login: String,

           @NotNull
           @NotEmpty
           var firstname : String,

           @NotNull
           @NotEmpty
           var lastname: String,

           @NotNull
           @NotEmpty
           var password : String,

           @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
           @OrderBy("creationTime desc")
           var posts : List<Post>
)
