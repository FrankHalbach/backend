package com.visteon.vfin.project.application

import com.visteon.vfin.project.model.Project
import java.util.UUID

data class ProjectResponse(val id : UUID, val name : String)

fun Project.toResponse(): ProjectResponse = ProjectResponse(this.id.value,this.projectNumber.value)