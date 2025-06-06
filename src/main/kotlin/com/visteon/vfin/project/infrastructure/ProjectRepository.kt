package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.common.crud.BaseGenericRepository
import com.visteon.vfin.common.types.NameField

import com.visteon.vfin.project.model.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


interface ProjectEntityRepository : CrudRepository<ProjectEntity, UUID>

@Repository
class ProjectRepository(
    private val crudRepo: ProjectEntityRepository
) : BaseGenericRepository<Project, ProjectEntity, ProjectId, UUID>(
    repo = crudRepo,
    toDomain = ProjectEntity::toDomain,
    toEntity = Project::toEntity,
    idToDb = { it.value }
)


fun Project.toEntity() = ProjectEntity(
        id = this.id.value,
        name = this.name.value
    )
fun ProjectEntity.toDomain() = Project(
        id = ProjectId(this.id),
        name = NameField.Companion(this.name)
    )
