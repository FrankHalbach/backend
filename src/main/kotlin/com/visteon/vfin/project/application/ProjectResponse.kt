package com.visteon.vfin.project.application

import com.visteon.vfin.project.model.Project
import java.util.UUID

data class ProjectResponse(
    val id : UUID,
    val projectNumber : String,
    val projectTitle : String
)

fun Project.toResponse(): ProjectResponse = ProjectResponse(this.id.value,this.projectNumber.value,this.projectTitle.value)