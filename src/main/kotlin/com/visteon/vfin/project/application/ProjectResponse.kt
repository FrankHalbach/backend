package com.visteon.vfin.project.application

import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectStatus
import java.util.*

data class ProjectResponse(
    val id : UUID,
    val projectNumber : String,
    val projectTitle : String,
    val status : ProjectStatus,
)

fun Project.toResponse(): ProjectResponse = ProjectResponse(
    this.id.value,
    this.projectNumber.value,
    this.projectTitle.value,
    this.projectStatus
)