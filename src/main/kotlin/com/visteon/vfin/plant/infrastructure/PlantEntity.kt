package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.types.NameField
import com.visteon.vfin.plant.model.Plant
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable


object PlantEntity : UUIDTable("PLANT") {
 val code = varchar("CODE", NameField.MAX_LENGTH).uniqueIndex()
 val name = varchar("NAME", NameField.MAX_LENGTH)
}


fun ResultRow.toDomain(): Plant = Plant.from(
    this[PlantEntity.id].value,
    this[PlantEntity.code],
    this[PlantEntity.name]
)
