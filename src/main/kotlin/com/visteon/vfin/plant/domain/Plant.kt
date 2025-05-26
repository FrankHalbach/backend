package com.visteon.vfin.plant.domain

import com.visteon.vfin.common.types.NameField

data class Plant(
    val plantId: PlantId,
    val code: NameField,
    val name: NameField
) {
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

