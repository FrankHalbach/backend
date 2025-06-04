package com.visteon.vfin.users.application

import com.visteon.vfin.users.model.AppUser

fun CreateAppUserRequest.toDomain(): AppUser =
    AppUser.Companion.new(
        appUserId = this.appUserId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )

fun UpdateAppUserRequest.toDomain(id: String): AppUser =
    AppUser.Companion.from(
        id = id,
        appUserId = this.appUserId,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        userStatus = this.userStatus
    )

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value.toString(),
    appUserId = this.appUserId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value,
    userStatus = this.userStatus
)