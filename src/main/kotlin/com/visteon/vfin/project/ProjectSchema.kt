package com.visteon.vfin.project

import com.visteon.vfin.project.infrastructure.ProjectEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object ProjectSchema {
    fun initialize() {
        SchemaUtils.create(ProjectEntity)
    }
}

