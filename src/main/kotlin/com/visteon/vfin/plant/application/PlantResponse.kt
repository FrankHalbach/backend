package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.model.Plant

data class PlantResponse(
    val id: String,
    val code: String,
    val name: String,
)

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value.toString(),
    code = this.code.value,
    name = this.name.value
)