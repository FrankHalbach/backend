package com.visteon.vfin.users.ui

import com.visteon.vfin.users.domain.AppUser

data class UserResponse(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun AppUser.toResponse(): UserResponse = UserResponse(
    userId = this.userId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value
)