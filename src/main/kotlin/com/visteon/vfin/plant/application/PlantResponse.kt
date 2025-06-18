package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.model.Plant
import java.time.Instant

data class PlantResponse(
    val id: String,
    val code: String,
    val name: String,
    val lastUpdatedBy: String,
    val lastUpdatedAt: Instant,
)

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value.toString(),
    code = this.code,
    name = this.name,
    lastUpdatedBy = this.audit.lastUpdatedBy.value.toString(),
    lastUpdatedAt = this.audit.lastUpdatedAt,
)