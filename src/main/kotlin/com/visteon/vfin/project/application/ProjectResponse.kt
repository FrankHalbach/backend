package com.visteon.vfin.project.application

import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectStatus
import java.util.*

data class ProjectResponse(
    val id : UUID,
    val projectNumber : String,
    val projectTitle : String,
    val projectStatus : ProjectStatus,
)

fun Project.toResponse(): ProjectResponse = ProjectResponse(
    this.id.value,
    this.projectNumber,
    this.projectTitle,
    this.projectStatus
)