package com.visteon.vfin.plant

import com.visteon.vfin.plant.infrastructure.PlantEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object PlantSchema {
    fun initialize(){
        SchemaUtils.create(PlantEntity)
    }
}