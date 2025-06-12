package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectStatus
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable


object ProjectEntity : UUIDTable("PROJECT") {
    val projectNumber = varchar("PROJECT_NUMBER", NameField.MAX_LENGTH).uniqueIndex()
    val projectTitle = varchar("PROJECT_TITLE", NameField.MAX_LENGTH)
    val projectStatus = enumerationByName("PROJECT_STATUS",64, ProjectStatus::class)
}


fun ResultRow.toProject(): Project = Project.from(
    this[ProjectEntity.id].value,
    this[ProjectEntity.projectNumber],
    this[ProjectEntity.projectTitle],
    this[ProjectEntity.projectStatus],
)
