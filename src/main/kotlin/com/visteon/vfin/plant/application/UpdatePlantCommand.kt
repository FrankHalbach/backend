package com.visteon.vfin.plant.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.plant.domain.Plant
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdatePlantCommand(

    @field:NotBlank
    @field:Size(min = 1,max = 64)
    val code: String,

    @field:NotBlank
    @field:Size(min = 1,max = 255)
    val name: String,
)

@Command
class UpdatePlantCommandHandler(
    private val plantRepository: PlantRepository
) {
    fun handle( id: Int, command: UpdatePlantCommand): Plant {
        val plant = Plant.from(id , command.code, command.name)
        return plantRepository.update(plant)
    }
}
