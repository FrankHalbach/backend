package com.visteon.vfin.plant.model

import com.visteon.vfin.common.Ids
import com.visteon.vfin.types.NameField
import java.util.UUID

data class Plant(
    val plantId: PlantId,
    val code: NameField,
    val name: NameField
) {
    fun update(code: String, name: String): Plant =
        this.copy(
            code = NameField(code),
            name = NameField(name),
        )


    companion object {
        fun from(plantId: UUID, code:String, name:String): Plant =
            Plant(
                plantId = PlantId(plantId),
                code= NameField(code),
                name = NameField(name)
            )

        fun new(code:String, name:String): Plant =
            this.from(Ids.new(),code,name)

    }
}

