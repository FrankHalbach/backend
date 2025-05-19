package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.domain.User
import com.visteon.vfin.users.domain.UserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = UserId.Companion.MAX_LENGTH)
    val userId: String ="",

    @Column(nullable = false, length = NameField.Companion.MAX_LENGTH)
    val firstName: String ="",

    @Column(nullable = false, length = NameField.Companion.MAX_LENGTH)
    val lastName: String ="",

    @Column(nullable = false, unique = true, length = EmailAddress.Companion.MAX_LENGTH)
    val email: String=""
)



// Domain ↔ Entity
fun UserEntity.toDomain(): User =
    User(
        userId = UserId(this.userId),
        firstName = NameField(this.firstName),
        lastName = NameField(this.lastName),
        email = NameField(this.email)
    )

fun User.toEntity(id:Long?): UserEntity =
    UserEntity(
        id = id, // Let DB auto-generate, or pass externally if needed
        userId = this.userId.value,
        firstName = this.firstName.value,
        lastName = this.lastName.value,
        email = this.email.value
    )