package com.pastebin.demo.domain

import jdk.jfr.BooleanFlag
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.transaction.TransactionScoped
import javax.validation.constraints.*

@Entity
class Post(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    var user: User?,

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    var hash: String,

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    var text: String,


    var ancestorPostId: Long =0,

    @CreationTimestamp
    private var creationTime: Date = Date(),

    @BooleanFlag
    var isPublic : Boolean = true
)