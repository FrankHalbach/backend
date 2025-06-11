package com.visteon.vfin.project.application

import com.visteon.vfin.common.types.NameField
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateProjectRequest (

    @NotBlank
    @Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val projectNumber: String,

    @NotBlank
    @Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val projectTitle: String,
)