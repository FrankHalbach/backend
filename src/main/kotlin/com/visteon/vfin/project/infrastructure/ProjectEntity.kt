package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.project.model.Project
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable


object ProjectEntity : UUIDTable("PROJECT") {
    val projectNumber = varchar("PROJECT_NUMBER", NameField.MAX_LENGTH).uniqueIndex()
    val projectTitle = varchar("PROJECT_TITLE", NameField.MAX_LENGTH)
}


fun ResultRow.toProject(): Project = Project.from(
    this[ProjectEntity.id].value,
    this[ProjectEntity.projectNumber],
    this[ProjectEntity.projectTitle]
)
