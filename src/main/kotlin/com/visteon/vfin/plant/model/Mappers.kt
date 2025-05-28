package com.visteon.vfin.plant.model

fun CreatePlantRequest.toDomain(): CreatePlant =
    CreatePlant.from(
        code = this.code,
        name = this.name
    )

fun UpdatePlantRequest.toDomain(id: Int): Plant =
    Plant.from(
        plantId = id,
        code = this.code,
        name = this.name
    )

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value,
    code = this.code.value,
    name = this.name.value
)