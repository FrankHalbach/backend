package com.visteon.vfin.plant.application

import com.visteon.vfin.common.exception.DuplicateEntityException
import com.visteon.vfin.common.exception.EntityNotFoundException
import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Transactional
@Service
class PlantService(
    val repository: PlantRepository
) {
    fun create(req: PlantCreationRequest): PlantId {

        if(repository.plantCodeExists(req.code))
            throw DuplicateEntityException("Plant","Code", req.code)

        val newPlant = Plant.new(
            code = req.code,
            name = req.name
        )
        return  repository.create(newPlant)
    }

    fun update(plantId: PlantId, req: PlantUpdateRequest) {

        val current = repository.getById(plantId) ?: throw EntityNotFoundException("Plant",plantId.value.toString())

        if(repository.plantCodeExistsOnOtherPlants(plantId,req.code))
            throw DuplicateEntityException("Plant","Code", req.code)

        val updatedPlant = current.update(req.name,req.code)
        repository.update(updatedPlant)
    }

    fun getById(plantId: PlantId): PlantResponse? = repository.getById(plantId)?.toResponse()

    fun getAll(): List<PlantResponse> =repository.getAll().map { it.toResponse() }
}

fun Plant.toResponse(): PlantResponse = PlantResponse(
    id = this.plantId.value.toString(),
    code = this.code.value,
    name = this.name.value
)