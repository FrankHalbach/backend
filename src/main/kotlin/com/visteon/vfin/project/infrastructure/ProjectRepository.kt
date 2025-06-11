package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository {

    fun create(newProject: Project): ProjectId {

        val id = ProjectEntity.insertAndGetId {
            it[projectNumber] = newProject.projectNumber.value
            it[projectTitle] = newProject.projectTitle.value
        }

        return ProjectId(id.value)

    }

    fun update(updated: Project)  {

        ProjectEntity.update({ ProjectEntity.id eq updated.id.value }) {
            it[projectNumber] = updated.projectNumber.value
            it[projectTitle] = updated.projectTitle.value
        }
    }

    fun getById(projectId: ProjectId): Project? = ProjectEntity
        .selectAll()
        .where(ProjectEntity.id eq  projectId.value)
        .firstOrNull()
        ?.toProject()

    fun getAll(): List<Project> = ProjectEntity.selectAll().map { it.toProject() }


}
