package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.plant.model.Plant
import com.visteon.vfin.plant.model.PlantId
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Repository


@Repository
class PlantRepository
{

    fun create(newPlant: Plant): PlantId {

        val id = PlantEntity.insertAndGetId {
            it[code] = newPlant.code.value
            it[name] = newPlant.name.value
        }

        return PlantId(id.value)

    }

    fun update(updated: Plant)  {

        PlantEntity.update({ PlantEntity.id eq updated.plantId.value }) {
            it[code] = updated.code.value
            it[name] = updated.name.value
        }
    }

    fun plantCodeExists(code: String): Boolean = PlantEntity
        .selectAll()
        .where { PlantEntity.code eq code }
        .limit(1)
        .empty()
        .not()

    fun plantCodeExistsOnOtherPlants(plantId: PlantId, code: String): Boolean = PlantEntity
        .selectAll()
        .where { (PlantEntity.code eq code) and (PlantEntity.id neq plantId.value) }
        .limit(1)
        .empty()
        .not()

    fun getById(plantId: PlantId): Plant? = PlantEntity
        .selectAll()
        .where(PlantEntity.id eq plantId.value)
        .firstOrNull()
        ?.toDomain()

    fun getAll(): List<Plant> = PlantEntity.selectAll().map { it.toDomain() }

}
