package com.visteon.vfin.users.application

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.UserStatus

data class UserResponse(
    val id: String,
    val appUserId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userStatus: UserStatus
)

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value.toString(),
    appUserId = this.appUserId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value,
    userStatus = this.userStatus
)