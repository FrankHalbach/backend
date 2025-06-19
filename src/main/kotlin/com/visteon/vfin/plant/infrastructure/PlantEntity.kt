package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.users.UserId
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.*



object PlantEntity : UUIDTable("plant") {
 val code = varchar("code", FieldLengths.LABEL_MAX).uniqueIndex()
 val name = varchar("name", FieldLengths.LABEL_MAX)
 val createdAt = timestamp("created_at")
 val createdBy = uuid("created_by")
 val modifiedAt = timestamp("modified_at").nullable()
 val modifiedBy = uuid("modified_by").nullable()
}


fun ResultRow.toDomain(): Plant = Plant.from(
    this[PlantEntity.id].value,
    this[PlantEntity.code],
    this[PlantEntity.name],
    this[PlantEntity.createdAt],
    UserId(this[PlantEntity.createdBy]),
    this[PlantEntity.modifiedAt],
    this[PlantEntity.modifiedBy]?.let { UserId(it) }
)
