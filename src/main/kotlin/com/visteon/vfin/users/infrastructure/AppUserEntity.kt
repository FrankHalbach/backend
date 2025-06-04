package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.Ids
import com.visteon.vfin.users.model.UserStatus
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "app_user")
data class UserEntity(

    @Id
    val id: UUID = Ids.empty(),

    @Column(nullable = false, unique = true)
    val appUserId: String ="",

    @Column(nullable = false)
    val firstName: String ="",

    @Column(nullable = false)
    val lastName: String ="",

    @Column(nullable = false, unique = true)
    val email: String="",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val userStatus: UserStatus  = UserStatus.ACTIVE
)



