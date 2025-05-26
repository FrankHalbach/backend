package com.visteon.vfin.plant.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.plant.domain.Plant

class GetAllPlantsQuery

@Query
class  GetAllPlantsQueryHandler(
    private val plantRepository: PlantRepository
) {
    fun handle(query:GetAllPlantsQuery = GetAllPlantsQuery()): List<Plant> {
        return plantRepository.getAll()
    }
}

