package com.visteon.vfin.user.infrastructure

import com.visteon.vfin.configuration.InitDev
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.stereotype.Component


@Component
internal class UserSchema {

    @InitDev(order = 1)
    fun createSchema() {
        SchemaUtils.create(
            AppUserEntity, AppUserRolesEntity
        )
    }

    @InitDev()
    fun seedData() {
        // Your seed data
    }
}