package com.visteon.vfin.users.ui

import com.visteon.vfin.users.domain.User

data class UserResponse(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun User.toResponse(): UserResponse = UserResponse(
    userId = this.userId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value
)