package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.types.EmailAddress
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.UserId
import com.visteon.vfin.users.model.UserStatus
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import com.visteon.vfin.common.FieldLengths

object AppUserEntity : UUIDTable("APP_USER") {
    val appUserId = varchar("APP_USER_ID", FieldLengths.UUID).uniqueIndex()
    val firstName = varchar("FIRST_NAME", FieldLengths.LABEL_MAX)
    val lastName = varchar("LAST_NAME", FieldLengths.LABEL_MAX)
    val email = varchar("EMAIL", EmailAddress.MAX_LENGTH).uniqueIndex()
    val userStatus = enumerationByName("USER_STATUS", FieldLengths.ENUM, UserStatus::class)
}

fun ResultRow.toAppUser(): AppUser = AppUser(
    id = UserId(this[AppUserEntity.id].value),
    appUserId = AppUserId(this[AppUserEntity.appUserId]),
    firstName = this[AppUserEntity.firstName],
    lastName = this[AppUserEntity.lastName],
    email = EmailAddress(this[AppUserEntity.email]),
    userStatus =  this[AppUserEntity.userStatus]
)
