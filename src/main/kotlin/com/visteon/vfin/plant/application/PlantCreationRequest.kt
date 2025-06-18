package com.visteon.vfin.plant.application

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import com.visteon.vfin.common.FieldLengths


data class PlantCreationRequest(
    
    @field:NotBlank
    @field:Size(max =  FieldLengths.LABEL_MAX)
    val code: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val name: String,

)    
