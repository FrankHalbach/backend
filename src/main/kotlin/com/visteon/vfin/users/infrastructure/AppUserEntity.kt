package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.types.EmailAddress
import com.visteon.vfin.types.NameField
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import com.visteon.vfin.users.model.UserStatus
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable

object AppUserEntity : UUIDTable("APP_USER") {
    val appUserId = varchar("APP_USER_ID", 16).uniqueIndex()
    val firstName = varchar("FIRST_NAME", NameField.MAX_LENGTH)
    val lastName = varchar("LAST_NAME", NameField.MAX_LENGTH)
    val email = varchar("EMAIL", EmailAddress.MAX_LENGTH).uniqueIndex()
    val userStatus = enumerationByName("USER_STATUS",64, UserStatus::class)
}

fun ResultRow.toAppUser(): AppUser = AppUser(
    id = UserId(this[AppUserEntity.id].value),
    appUserId = AppUserId(this[AppUserEntity.appUserId]),
    firstName = NameField(this[AppUserEntity.firstName]),
    lastName = NameField(this[AppUserEntity.lastName]),
    email = EmailAddress(this[AppUserEntity.email]),
    userStatus =  this[AppUserEntity.userStatus]
)
