package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import com.visteon.vfin.project.model.ProjectStatus
import com.visteon.vfin.common.FieldLengths
import org.jetbrains.exposed.v1.core.ColumnTransformer
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.Table
import java.util.*


object ProjectEntity : Table("PROJECT") {
    val id = uuid("ID").uniqueIndex().transform(ProjectIdTransformer())
    val projectNumber = varchar("PROJECT_NUMBER", FieldLengths.LABEL_MAX).uniqueIndex()
    val projectTitle = varchar("PROJECT_TITLE",  FieldLengths.LABEL_MAX)
    val projectStatus = enumerationByName("PROJECT_STATUS",FieldLengths.ENUM, ProjectStatus::class)
}


fun ResultRow.toProject(): Project = Project(
    this[ProjectEntity.id],
    this[ProjectEntity.projectNumber],
    this[ProjectEntity.projectTitle],
    this[ProjectEntity.projectStatus],
)


class ProjectIdTransformer : ColumnTransformer<UUID, ProjectId> {
    override fun wrap(value: UUID) = ProjectId(value)
    override fun unwrap(value: ProjectId): UUID  = value.value
}


