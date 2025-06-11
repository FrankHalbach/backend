package com.visteon.vfin.project.model

import com.visteon.vfin.common.crud.EntityId
import com.visteon.vfin.common.types.NameField
import java.util.UUID

data class Project (
    override val id: ProjectId,
    val projectNumber: NameField,
    val projectTitle: NameField,
    //val audit: AuditInfo,
): EntityId<ProjectId> {
    fun updateFrom( projectNumber: String, projectTitle: String) : Project {

        return this.copy(
            projectNumber = NameField(projectNumber),
            projectTitle = NameField(projectTitle)
        )
    }
    companion object {
        fun from(id: UUID, projectNumber: String, projectTitle: String): Project {
            return Project(
                id = ProjectId(id),
                projectNumber = NameField(projectNumber),
                projectTitle = NameField(projectTitle)
                //audit = AuditInfo()
            )
        }
        fun new(projectNumber: String, projectTitle: String) : Project {
            return Project(
                id = ProjectId.new(),
                projectNumber = NameField(projectNumber),
                projectTitle = NameField(projectTitle))
        }
    }

}
