package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.PlantId
import org.springframework.stereotype.Service

@Service
class PlantService(
    private val repository: PlantRepository
) {

    fun create(req: CreatePlantRequest): PlantResponse {
        val plant = repository.create(req.toDomain())
        return plant.toResponse()
    }

    fun update(plantId: Int, req: UpdatePlantRequest): PlantResponse {
        val updated = repository.update(req.toDomain(plantId))
        return updated.toResponse()
    }

    fun getById(plantId: Int): PlantResponse? {
        return repository.getById(PlantId(plantId))?.toResponse()

    }

    fun getAll(): List<PlantResponse> {
        return repository.getAll().map { it.toResponse() }
    }
}
