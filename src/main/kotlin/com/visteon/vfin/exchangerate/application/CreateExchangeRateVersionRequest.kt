package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.common.FieldLengths
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateExchangeRateVersionRequest(

    @field:NotBlank
    @field:Size(max =  FieldLengths.LABEL_MAX)
    val versionName: String,

    @field:NotBlank
    @field:Size(max =  FieldLengths.DESCRIPTION_MAX)
    val description: String

)


