package com.visteon.vfin.users.application

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.UserStatus
import com.visteon.vfin.users.model.UserRole

data class UserResponse(
    val id: String,
    val appUserId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userStatus: UserStatus,
    val userRoles: Set<UserRole>
)

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value.toString(),
    appUserId = this.appUserId.value,
    firstName = this.firstName.toString(),
    lastName = this.lastName.toString(),
    email = this.email.value,
    userStatus = this.userStatus,
    userRoles = this.userRoles

)