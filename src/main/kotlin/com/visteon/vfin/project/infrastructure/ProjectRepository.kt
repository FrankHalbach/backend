package com.visteon.vfin.project.infrastructure


import com.visteon.vfin.types.NameField
import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository {

    fun create(request: Project) = ProjectEntity
        .insert {
            it[id] = request.id
            it[projectNumber] = request.projectNumber
            it[projectTitle] = request.projectTitle
            it[projectStatus] = request.projectStatus
        }


    fun update(request: Project) = ProjectEntity
        .update({ ProjectEntity.id eq request.id }) {
            it[projectNumber] = request.projectNumber
            it[projectTitle] = request.projectTitle
            it[projectStatus] = request.projectStatus
        }

    fun projectNumberExists(projectNumber: NameField): Boolean = ProjectEntity
        .selectAll()
        .where { ProjectEntity.projectNumber eq projectNumber }
        .limit(1)
        .empty()
        .not()

    fun projectNumberExistsForOtherProjects(projectId: ProjectId, projectNumber: NameField): Boolean =
        ProjectEntity
            .selectAll()
            .where { (ProjectEntity.projectNumber eq projectNumber) and (ProjectEntity.id neq projectId) }
            .limit(1)
            .empty()
            .not()

     fun getById(id: ProjectId): Project? = ProjectEntity
        .selectAll()
        .where(ProjectEntity.id eq  id)
        .firstOrNull()
        ?.toProject()

     fun getAll(): List<Project> = ProjectEntity.selectAll().map { it.toProject() }


}
