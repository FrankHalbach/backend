package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.sharedkernel.identifiers.UserId
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.*



object PlantEntity : UUIDTable("PLANT") {
 val code = varchar("CODE", FieldLengths.LABEL_MAX).uniqueIndex()
 val name = varchar("NAME", FieldLengths.LABEL_MAX)
 val createdAt = timestamp("CREATED_AT")
 val createdBy = uuid("CREATED_BY")
 val modifiedAt = timestamp("MODIFIED_AT").nullable()
 val modifiedBy = uuid("MODIFIED_BY").nullable()
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
