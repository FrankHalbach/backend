package com.visteon.vfin.user

import com.visteon.vfin.user.infrastructure.AppUserEntity
import com.visteon.vfin.user.infrastructure.AppUserRolesEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object UserSchema {
    fun initialize() {
        SchemaUtils.create(AppUserEntity, AppUserRolesEntity)
    }
}