package com.visteon.vfin.plant.application

import com.visteon.vfin.plant.domain.CreatePlant
import com.visteon.vfin.plant.domain.Plant
import com.visteon.vfin.plant.domain.PlantId

//here we can extract a generic interface.
interface PlantRepository {
    fun create(plant: CreatePlant): Plant
    fun update(updated: Plant): Plant
    fun getById(plantId: PlantId): Plant?
    fun getAll(): List<Plant>
}