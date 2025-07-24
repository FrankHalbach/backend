package com.visteon.vfin.user.application

import com.visteon.vfin.user.model.AppUser
import com.visteon.vfin.user.model.UserStatus
import com.visteon.vfin.user.model.UserRole
import java.util.UUID

data class UserResponse(
    val id: UUID,
    val appUserId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userStatus: UserStatus,
    val userRoles: Set<UserRole>
)

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value,
    appUserId = this.appUserId.value,
    firstName = this.firstName.toString(),
    lastName = this.lastName.toString(),
    email = this.email.value,
    userStatus = this.userStatus,
    userRoles = this.userRoles

)