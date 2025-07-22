package com.visteon.vfin.user.infrastructure

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.user.model.UserRole
import org.jetbrains.exposed.v1.core.dao.id.CompositeIdTable

object AppUserRolesEntity : CompositeIdTable("APP_USER_ROLE") {
    val userId = reference("USER_ID", AppUserEntity.id)
    val role = enumerationByName("ROLE", FieldLengths.ENUM, UserRole::class).entityId()

    override val primaryKey = PrimaryKey(userId, role)
}