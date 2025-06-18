package com.visteon.vfin.plant.application

import com.visteon.vfin.common.FieldLengths
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PlantUpdateRequest(

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val code: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val name: String,
)