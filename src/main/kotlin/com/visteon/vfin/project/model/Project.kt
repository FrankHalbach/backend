package com.visteon.vfin.project.model

data class Project (
    val id: ProjectId,
    val projectNumber: String,
    val projectTitle: String,
    val projectStatus: ProjectStatus,    
    //val audit: AuditInfo,
){
    fun update(projectNumber: String, projectTitle: String, projectStatus: ProjectStatus) : Project =
        this.copy(
            projectNumber = projectNumber,
            projectTitle = projectTitle,
            projectStatus = projectStatus)


    companion object {
        fun new(projectNumber: String, projectTitle: String) : Project =
            Project(
                id = ProjectId.new(),
                projectNumber = projectNumber,
                projectTitle = projectTitle,
                projectStatus= ProjectStatus.ACTIVE
            )
    }

}

