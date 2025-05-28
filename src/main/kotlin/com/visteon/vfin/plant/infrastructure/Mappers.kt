package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId

// Domain ↔ Entity
fun PlantEntity.toDomain(): Plant =
    Plant(
        plantId = PlantId(this.id ?: 0),
        code = NameField(this.code),
        name = NameField(this.name),

        )

fun Plant.toEntity(id:Int?): PlantEntity =
    PlantEntity(
        id = id, // Let DB auto-generate, or pass externally if needed
        code= this.code.value,
        name=this.name.value
    )