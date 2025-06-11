package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Repository


@Repository
class AppUserRepository {

    fun create(user: AppUser): UserId {

        val id = AppUserEntity.insertAndGetId {
            it[id] = user.id.value
            it[appUserId] = user.appUserId.value
            it[firstName] = user.firstName.value
            it[lastName] = user.lastName.value
            it[email] = user.email.value
            it[userStatus] = user.userStatus
        }

        return UserId(id.value)
   }

    fun update(user: AppUser) {
        AppUserEntity.update({ AppUserEntity.id eq user.id.value }) {
            it[appUserId] = user.appUserId.value
            it[firstName] = user.firstName.value
            it[lastName] = user.lastName.value
            it[email] = user.email.value
            it[userStatus] = user.userStatus
        }
    }


    fun getById(userId: UserId): AppUser? = AppUserEntity
        .selectAll()
        .where { AppUserEntity.id eq userId.value }
        .firstOrNull()
        ?.toAppUser()

    fun getByAppUserId(userId: AppUserId): AppUser? = AppUserEntity
        .selectAll()
        .where { AppUserEntity.appUserId eq userId.value }
        .firstOrNull()
        ?.toAppUser()

    fun getAll(): List<AppUser> = AppUserEntity.selectAll().map { it.toAppUser() }

}

