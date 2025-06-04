package com.visteon.vfin.plant.application

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdatePlantRequest(

    @field:NotBlank
    @field:Size(min = 1,max = 64)
    val code: String,

    @field:NotBlank
    @field:Size(min = 1,max = 255)
    val name: String,
)