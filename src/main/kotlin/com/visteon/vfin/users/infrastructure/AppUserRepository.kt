package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.UserRole
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.EmailAddress
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.batchInsert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.update
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.springframework.stereotype.Repository


@Repository
class AppUserRepository {

    fun create(user: AppUser) {
        AppUserEntity.insert {
            it[id] = user.id.value
            it[appUserId] = user.appUserId.value
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email.value
            it[userStatus] = user.userStatus
        }
        insertUserRoles(user.id, user.userRoles)
    }

    fun update(user: AppUser) {
        AppUserEntity.update({ AppUserEntity.id eq user.id.value }) {
            it[appUserId] = user.appUserId.value
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email.value
            it[userStatus] = user.userStatus
        }
        updateUserRoles(user.id, user.userRoles)
    }

    fun appUserIdExists(appUserId: AppUserId): Boolean = AppUserEntity
        .selectAll()
        .where { AppUserEntity.appUserId eq appUserId.value }
        .limit(1)
        .empty()
        .not()

    fun appUserIdExistsOnOtherUser(userId: UserId, appUserId: AppUserId): Boolean = AppUserEntity
        .selectAll()
        .where {(AppUserEntity.appUserId eq appUserId.value) and (AppUserEntity.id neq userId.value)}
        .limit(1)
        .empty()
        .not()

    fun emailAddressExists(email: String):Boolean = AppUserEntity
        .selectAll()
        .where { AppUserEntity.email eq email }
        .limit(1)
        .empty()
        .not()

    fun getById(userId: UserId): AppUser? = AppUserEntity
        .selectAll()
        .where { AppUserEntity.id eq userId.value }
        .firstOrNull()        
        ?.toAppUser(fetchUserRoles(userId))


    fun getByAppUserId(appUserId: AppUserId): AppUser? = AppUserEntity
        .selectAll()
        .where { AppUserEntity.appUserId eq appUserId.value }
        .firstOrNull()
        ?. let { 
            val userId = UserId(it[AppUserEntity.id].value)
            val userRoles = fetchUserRoles(userId)            
            it.toAppUser(userRoles)
         }        
    


    private fun fetchUserRoles(userId: UserId): Set<UserRole> =
        AppUserRolesEntity
            .select( AppUserRolesEntity.role)
            .where { AppUserRolesEntity.userId eq userId.value }      
            .map { it[AppUserRolesEntity.role] }      
            .toSet()   

    fun getAll(): List<AppUser> =
        (AppUserEntity innerJoin AppUserRolesEntity)
            .selectAll()
            .groupBy { it[AppUserEntity.id] }
            .map { (userId, rows) ->
                val firstRow = rows.first()
                val roles = rows.map { it[AppUserRolesEntity.role] }.toSet()
                firstRow.toAppUser(roles)
            }


    private fun insertUserRoles(userId: UserId, roles: Set<UserRole>) {
        AppUserRolesEntity.batchInsert(roles) { role ->
            this[AppUserRolesEntity.userId] = userId.value
            this[AppUserRolesEntity.role] = role
        }
    }

    private fun updateUserRoles(userId: UserId, newRoles: Set<UserRole>) {
        AppUserRolesEntity.deleteWhere { AppUserRolesEntity.userId eq userId.value }
        insertUserRoles(userId, newRoles)
    }


}

private fun ResultRow.toAppUser(userRoles: Set<UserRole>): AppUser = AppUser.load(
    id = UserId(this[AppUserEntity.id].value),
    appUserId = AppUserId(this[AppUserEntity.appUserId]),
    firstName = this[AppUserEntity.firstName],
    lastName = this[AppUserEntity.lastName],
    email = EmailAddress(this[AppUserEntity.email]),
    userStatus = this[AppUserEntity.userStatus],
    userRoles = userRoles
)