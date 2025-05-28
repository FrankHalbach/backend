package com.visteon.vfin.plant.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreatePlantRequest(

    @field:NotBlank
    @field:Size(min = 1,max = 64)
    val code: String,

    @field:NotBlank
    @field:Size(min = 1,max = 255)
    val name: String,
)