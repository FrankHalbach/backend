package com.visteon.vfin.project.application

import com.visteon.vfin.types.NameField
import com.visteon.vfin.project.model.ProjectStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ProjectUpdateRequest (

    @field:NotBlank
    @field:Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val projectNumber: String,

    @field:NotBlank
    @field:Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val projectTitle: String,

    val projectStatus: ProjectStatus,
)