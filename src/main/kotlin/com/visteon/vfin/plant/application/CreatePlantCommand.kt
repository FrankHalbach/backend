package com.visteon.vfin.plant.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.plant.domain.CreatePlant
import com.visteon.vfin.plant.domain.Plant
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreatePlantCommand(

    @field:NotBlank
    @field:Size(min = 1,max = 64)
    val code: String,

    @field:NotBlank
    @field:Size(min = 1,max = 255)
    val name: String,
)

@Command
class CreatePlantCommandHandler(
    private val plantRepository: PlantRepository
) {
    fun handle(command: CreatePlantCommand): Plant {
        val plant = CreatePlant.from(command.code, command.name)
        return plantRepository.create(plant)
    }
}


