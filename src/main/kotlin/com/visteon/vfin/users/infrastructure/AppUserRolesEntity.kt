package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.model.UserRole
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import com.visteon.vfin.common.FieldLengths

object AppUserRolesEntity : Table("APP_USER_ROLE") {
    val userId = reference("USER_ID", AppUserEntity.id)
    val role = enumerationByName("ROLE", FieldLengths.ENUM, UserRole::class)

    override val primaryKey = PrimaryKey(userId, role)
}