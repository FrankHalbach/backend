package com.visteon.vfin.plant.application

import com.visteon.vfin.exception.DuplicateEntityException
import com.visteon.vfin.exception.EntityNotFoundException
import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import com.visteon.vfin.sharedkernel.identifiers.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.visteon.vfin.UserContext



@Transactional
@Service
class PlantService(
    val repository: PlantRepository,
    val userContext: UserContext    
) {
    fun create(req: PlantCreationRequest): Plant {
        
        if(repository.plantCodeExists(req.code))
            throw DuplicateEntityException("Plant","Code", req.code)

        val newPlant = Plant.new(
            code = req.code,
            name = req.name,    
            userContext.currentUserId()      
        )

        repository.create(newPlant)

        return newPlant
    }

    fun update(plantId: PlantId, req: PlantUpdateRequest): Plant {

        val current = repository.getById(plantId) ?: throw EntityNotFoundException("Plant",plantId.value.toString())

        if(repository.plantCodeExistsOnOtherPlants(plantId,req.code))
            throw DuplicateEntityException("Plant","Code", req.code)

        val updatedPlant = current.update(req.name,req.code,userContext.currentUserId())

        repository.update(updatedPlant)

        return updatedPlant
    }

    fun getById(plantId: PlantId): Plant? = repository.getById(plantId)

    fun getAll(): List<Plant> =repository.getAll()
}

