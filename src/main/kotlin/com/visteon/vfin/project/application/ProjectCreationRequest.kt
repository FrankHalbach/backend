package com.visteon.vfin.project.application

import com.visteon.vfin.common.FieldLengths
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ProjectCreationRequest (

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val projectNumber: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val projectTitle: String,
)

