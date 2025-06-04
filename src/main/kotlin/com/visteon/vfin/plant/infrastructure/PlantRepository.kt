package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.plant.model.CreatePlant
import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface PlantEntityRepository : CrudRepository<PlantEntity, Int> {
}
@Repository
class PlantRepository(
    private val crudRepo: PlantEntityRepository
)  {

     fun create(plant: CreatePlant): Plant {
         val entity = PlantEntity(
             null,
             plant.code.value,
             plant.name.value
         )

        return crudRepo.save(entity).toDomain()
    }

     fun getById(plantId: PlantId): Plant? {
        return crudRepo.findById(plantId.value)
            .orElse(null)
            ?.toDomain()
    }

     fun update(updated: Plant): Plant {

        val entity =  PlantEntity(
            id = updated.plantId.value,
            code = updated.code.value,
            name = updated.name.value
        )

        return crudRepo.save(entity).toDomain()
    }

    fun getAll(): List<Plant> {
        return crudRepo.findAll().map { it.toDomain() }
    }

}

// Domain ↔ Entity
fun PlantEntity.toDomain(): Plant =
    Plant(
        plantId = PlantId(this.id ?: 0),
        code = NameField(this.code),
        name = NameField(this.name)
        )