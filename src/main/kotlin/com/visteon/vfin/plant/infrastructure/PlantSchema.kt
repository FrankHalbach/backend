package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.configuration.InitDev
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.stereotype.Component


@Component
internal class PlantSchema {

    @InitDev()
    fun createSchema() {
        SchemaUtils.create(PlantEntity)
    }

    @InitDev()
    fun seedData() {
        // Your seed data
    }
}