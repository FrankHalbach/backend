package com.visteon.vfin.plant.ui

import com.visteon.vfin.plant.domain.Plant

data class PlantResponse(
    val id: Int,
    val code: String,
    val name: String,
)

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value,
    code = this.code.value,
    name = this.name.value
)


