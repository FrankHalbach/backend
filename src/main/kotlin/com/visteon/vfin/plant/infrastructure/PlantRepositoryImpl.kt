package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.plant.application.PlantRepository
import com.visteon.vfin.plant.domain.CreatePlant
import com.visteon.vfin.plant.domain.Plant
import com.visteon.vfin.plant.domain.PlantId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface PlantEntityRepository : CrudRepository<PlantEntity, Int> {
}

@Repository
class PlantRepositoryImpl(
    private val crudRepo: PlantEntityRepository
) : PlantRepository {

    override fun create(plant: CreatePlant): Plant {
        return crudRepo.save(PlantEntity(null,plant.code.value,plant.name.value)).toDomain()
    }

    override fun getById(plantId: PlantId): Plant? {
        return crudRepo.findById(plantId.value)
            .orElse(null)
            ?.toDomain()
    }

    override fun update(updated: Plant): Plant {
        val plantInDb = crudRepo.findById(updated.plantId.value)
            .orElseThrow { NoSuchElementException("Plant with ID ${updated.plantId.value} not found") }

        val entity = updated.toEntity(plantInDb.id)
        return crudRepo.save(entity).toDomain()
    }

    override fun getAll(): List<Plant> {
        return crudRepo.findAll().map { it.toDomain() }
    }

}