package com.visteon.vfin.users.infrastructure

import jakarta.persistence.*

@Table(name = "app_user")
@Entity
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false, unique = true)
    val userId: String ="",

    @Column(nullable = false)
    val firstName: String ="",

    @Column(nullable = false)
    val lastName: String ="",

    @Column(nullable = false, unique = true)
    val email: String=""
)



