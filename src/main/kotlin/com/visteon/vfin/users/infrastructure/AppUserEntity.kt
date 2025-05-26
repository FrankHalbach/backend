package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.domain.AppUser
import com.visteon.vfin.users.domain.AppUserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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



// Domain ↔ Entity
fun UserEntity.toDomain(): AppUser =
    AppUser(
        userId = AppUserId(this.userId),
        firstName = NameField(this.firstName),
        lastName = NameField(this.lastName),
        email = EmailAddress(this.email)
    )

fun AppUser.toEntity(id:Int?): UserEntity =
    UserEntity(
        id = id, // Let DB auto-generate, or pass externally if needed
        userId = this.userId.value,
        firstName = this.firstName.value,
        lastName = this.lastName.value,
        email = this.email.value
    )