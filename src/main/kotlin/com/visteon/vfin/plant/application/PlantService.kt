package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.CreatePlant
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import org.springframework.stereotype.Service

@Service
class PlantService(
    private val repository: PlantRepository
) {

    fun create(req: CreatePlantRequest): PlantResponse {

        val newPlant = CreatePlant.from(
            code = req.code,
            name = req.name
        )
        val plant = repository.create(newPlant)
        return plant.toResponse()
    }

    fun update(plantId: Int, req: UpdatePlantRequest): PlantResponse {

        val updatedRequest = Plant.from(
            plantId = plantId,
            code = req.code,
            name = req.name
        )

        val plantInDb = repository.getById(PlantId(plantId)) ?: throw NoSuchElementException("Plant with ID $plantId not found")

        val updatedPlant= plantInDb.updateFrom(updatedRequest)

        val updated = repository.update(updatedPlant)

        return updated.toResponse()
    }

    fun getById(plantId: Int): PlantResponse? {
        return repository.getById(PlantId(plantId))?.toResponse()

    }

    fun getAll(): List<PlantResponse> {
        return repository.getAll().map { it.toResponse() }
    }
}

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value,
    code = this.code.value,
    name = this.name.value
)