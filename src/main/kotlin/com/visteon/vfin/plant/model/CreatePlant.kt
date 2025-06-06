package com.visteon.vfin.plant.model

import com.visteon.vfin.common.types.NameField

    data class CreatePlant(
        val code: NameField,
        val name: NameField
    ){
        companion object {
            fun from(code:String, name:String): CreatePlant {
                return CreatePlant(
                    code= NameField.Companion(code),
                    name = NameField.Companion(name)
                )
            }
        }
    }