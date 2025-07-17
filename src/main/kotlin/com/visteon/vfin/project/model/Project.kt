package com.visteon.vfin.project.model

data class Project (
    val id: ProjectId,
    val projectNumber: String, // extract into value type
    val projectTitle: String,  // extract into value type
    val projectStatus: ProjectStatus,    
    //val audit: AuditInfo,
)
