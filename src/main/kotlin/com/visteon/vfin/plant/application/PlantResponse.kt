package com.visteon.vfin.plant.application

data class PlantResponse(
    val id: String,
    val code: String,
    val name: String,
)

data class PlantCreatedResponse(
    val id: String
)