package com.pastebin.demo.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
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
    var text: String,

    @CreationTimestamp
    private var creationTime: Date = Date()
)