package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.types.NameField
import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import com.visteon.vfin.project.model.ProjectStatus
import org.jetbrains.exposed.v1.core.ColumnTransformer
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.Table
import java.util.*


object ProjectEntity : Table("PROJECT") {
    val id = uuid("ID").uniqueIndex().transform(ProjectIdTransformer())
    val projectNumber = varchar("PROJECT_NUMBER", NameField.MAX_LENGTH).uniqueIndex().transform(NameFieldTransformer())
    val projectTitle = varchar("PROJECT_TITLE", NameField.MAX_LENGTH).transform(NameFieldTransformer())
    val projectStatus = enumerationByName("PROJECT_STATUS",64, ProjectStatus::class)
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

class NameFieldTransformer : ColumnTransformer<String, NameField> {
    override fun wrap(value: String) = NameField(value)
    override fun unwrap(value: NameField): String  = value.value
}