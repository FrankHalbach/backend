package com.visteon.vfin.users.application

import com.visteon.vfin.users.model.UserStatus

data class UserResponse(
    val id: String,
    val appUserId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userStatus: UserStatus
)
