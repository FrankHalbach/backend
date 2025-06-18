package com.visteon.vfin.project.application

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.project.model.ProjectStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ProjectUpdateRequest (

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val projectNumber: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val projectTitle: String,

    val projectStatus: ProjectStatus,
)