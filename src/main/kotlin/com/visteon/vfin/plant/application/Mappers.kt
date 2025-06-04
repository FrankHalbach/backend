package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.model.CreatePlant
import com.visteon.vfin.plant.model.Plant

fun CreatePlantRequest.toDomain(): CreatePlant =
    CreatePlant.Companion.from(
        code = this.code,
        name = this.name
    )

fun UpdatePlantRequest.toDomain(id: Int): Plant =
    Plant.Companion.from(
        plantId = id,
        code = this.code,
        name = this.name
    )

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value,
    code = this.code.value,
    name = this.name.value
)