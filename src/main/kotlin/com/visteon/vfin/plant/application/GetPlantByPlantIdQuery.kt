package com.visteon.vfin.plant.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.plant.domain.Plant
import com.visteon.vfin.plant.domain.PlantId

data class GetPlantByPlantIdQuery(val plantId:PlantId)

@Query
class  GetPlantsByPlantIdQueryHandler(
    private val plantRepository: PlantRepository
) {
    fun handle(query: GetPlantByPlantIdQuery): Plant? {
        return plantRepository.getById(query.plantId)
    }
}