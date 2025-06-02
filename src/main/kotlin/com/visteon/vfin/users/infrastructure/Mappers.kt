package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId

// Domain ↔ Entity
fun UserEntity.toDomain(): AppUser =
    AppUser(
        id = UserId(this.id),
        userId = AppUserId(this.userId),
        firstName = NameField(this.firstName),
        lastName = NameField(this.lastName),
        email = EmailAddress(this.email)
    )

fun AppUser.toEntity(): UserEntity =
    UserEntity(
        id = this.id.value,
        userId = this.userId.value,
        firstName = this.firstName.value,
        lastName = this.lastName.value,
        email = this.email.value
    )

fun AppUser.updatedFrom(updated: AppUser): AppUser {
    require(this.id == updated.id) { "Cannot update: ID mismatch" }

    return this.copy(
        userId = updated.userId,
        firstName = updated.firstName,
        lastName = updated.lastName,
        email = updated.email
    )
}