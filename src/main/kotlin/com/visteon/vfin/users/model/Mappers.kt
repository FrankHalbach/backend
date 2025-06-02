package com.visteon.vfin.users.model

import java.util.UUID

fun CreateAppUserRequest.toDomain(): AppUser =
    AppUser.new(
        userId = this.userId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )

fun UpdateAppUserRequest.toDomain(id: String): AppUser =
    AppUser.from(
        id = id,
        userId = this.userId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value.toString(),
    userId = this.userId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value
)