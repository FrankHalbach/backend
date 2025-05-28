package com.visteon.vfin.users.model

fun CreateAppUserRequest.toDomain(): AppUser =
    AppUser.from(
        userId = this.userId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )

fun UpdateAppUserRequest.toDomain(userId: String): AppUser =
    AppUser.from(
        userId = userId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )

fun AppUser.toResponse(): UserResponse = UserResponse(
    userId = this.userId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value
)