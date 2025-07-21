package com.visteon.vfin.users

import com.visteon.vfin.users.infrastructure.AppUserEntity
import com.visteon.vfin.users.infrastructure.AppUserRolesEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object UserSchema {
    fun initialize() {
        SchemaUtils.create(AppUserEntity, AppUserRolesEntity)
    }
}