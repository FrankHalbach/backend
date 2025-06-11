package com.visteon.vfin.plant.application

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreatePlantRequest(

    @NotBlank
    @Size(min = 1,max = 64)
    val code: String,

    @NotBlank
    @Size(min = 1,max = 255)
    val name: String,
)