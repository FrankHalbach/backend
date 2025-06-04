package com.visteon.vfin.plant.model

import com.visteon.vfin.common.types.NameField

data class Plant(
    val plantId: PlantId,
    val code: NameField,
    val name: NameField
) {
    fun updateFrom(updated: Plant): Plant {
        require(this.plantId == updated.plantId) { "Cannot update: Plant ID mismatch" }

        return this.copy(
            code = updated.code,
            name = updated.name
        )
    }

    companion object {
        fun from(plantId:Int, code:String, name:String): Plant {
            return Plant(
                plantId = PlantId(plantId),
                code= NameField(code),
                name = NameField(name)
            )
        }
    }
}

