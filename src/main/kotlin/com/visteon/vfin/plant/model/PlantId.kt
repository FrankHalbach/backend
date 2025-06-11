package com.visteon.vfin.plant.model

import com.visteon.vfin.common.Ids
import java.util.UUID

@JvmInline
value class PlantId(val value: UUID) {
    companion object {
        fun new() = PlantId(Ids.new())
        fun from(id:String) = PlantId(UUID.fromString(id))
    }
}