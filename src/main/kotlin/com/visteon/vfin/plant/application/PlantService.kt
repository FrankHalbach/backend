package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Transactional
@Service
class PlantService(
    val repository: PlantRepository
) {

    fun create(req: CreatePlantRequest): PlantId {

        val newPlant = Plant.new(
            code = req.code,
            name = req.name
        )
        return  repository.create(newPlant)
    }

    fun update(plantId: UUID, req: UpdatePlantRequest) {

        val updatedRequest = Plant.from(
            plantId = plantId,
            code = req.code,
            name = req.name
        )

        repository.update(updatedRequest)

    }

    fun getById(plantId: UUID): PlantResponse? = repository.getById(PlantId(plantId))?.toResponse()

    fun getAll(): List<PlantResponse> =repository.getAll().map { it.toResponse() }
}

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value.toString(),
    code = this.code.value,
    name = this.name.value
)