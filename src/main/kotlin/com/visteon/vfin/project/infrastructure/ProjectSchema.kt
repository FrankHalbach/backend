package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.configuration.InitDev
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.stereotype.Component


@Component
internal class ProjectSchema {

    @InitDev
    fun createSchema() {
        SchemaUtils.create(ProjectEntity)
    }

    @InitDev
    fun seedData() {
        // Your seed data
    }
}
