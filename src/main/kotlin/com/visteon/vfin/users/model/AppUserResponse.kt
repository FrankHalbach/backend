package com.visteon.vfin.users.model

data class UserResponse(
    val id: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
