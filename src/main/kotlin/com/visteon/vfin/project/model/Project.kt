package com.visteon.vfin.project.model

import com.visteon.vfin.types.NameField

data class Project (
    val id: ProjectId,
    val projectNumber: NameField,
    val projectTitle: NameField,
    val projectStatus: ProjectStatus,
    //val audit: AuditInfo,
){
    fun update(projectNumber: String, projectTitle: String, projectStatus: ProjectStatus) : Project =
        this.copy(
            projectNumber = NameField(projectNumber),
            projectTitle = NameField(projectTitle),
            projectStatus = projectStatus)


    companion object {
//        fun from(id: UUID, projectNumber: String, projectTitle: String, projectStatus: ProjectStatus): Project =
//            Project(
//                id = ProjectId(id),
//                projectNumber = NameField(projectNumber),
//                projectTitle = NameField(projectTitle),
//                projectStatus = projectStatus
//                //audit = AuditInfo()
//            )

        fun new(projectNumber: String, projectTitle: String) : Project =
            Project(
                id = ProjectId.new(),
                projectNumber = NameField(projectNumber),
                projectTitle = NameField(projectTitle),
                projectStatus= ProjectStatus.ACTIVE
            )
    }

}

