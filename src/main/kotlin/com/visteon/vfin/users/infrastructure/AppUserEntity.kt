package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.Ids
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "app_user")
data class UserEntity(

    @Id
    val id: UUID = Ids.empty(),

    @Column(nullable = false, unique = true)
    val userId: String ="",

    @Column(nullable = false)
    val firstName: String ="",

    @Column(nullable = false)
    val lastName: String ="",

    @Column(nullable = false, unique = true)
    val email: String=""
)



