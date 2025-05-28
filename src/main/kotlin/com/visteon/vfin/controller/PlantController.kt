package com.visteon.vfin.controller

import com.visteon.vfin.plant.model.CreatePlantRequest
import com.visteon.vfin.plant.infrastructure.PlantRepository
import com.visteon.vfin.plant.model.UpdatePlantRequest
import com.visteon.vfin.plant.model.PlantId
import com.visteon.vfin.plant.model.PlantResponse
import com.visteon.vfin.plant.model.toDomain
import com.visteon.vfin.plant.model.toResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plants")
class PlantController(
    private val repository : PlantRepository
) {

    @PostMapping
    fun createPlant(@Valid @RequestBody req: CreatePlantRequest): ResponseEntity<PlantResponse> {

        val plant = repository.create(req.toDomain())
        return  ResponseEntity.ok(plant.toResponse())
    }

    @PutMapping("/{plantId}")
    fun updatePlant(
        @PathVariable plantId: Int,
        @Valid @RequestBody req: UpdatePlantRequest
    ): ResponseEntity<PlantResponse> {
        val updated = repository.update(req.toDomain(plantId))
        return ResponseEntity.ok(updated.toResponse())
    }

    @GetMapping("/{plantId}")
    fun getPlant(@PathVariable plantId: Int): ResponseEntity<PlantResponse> =
        repository.getById(PlantId(plantId))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> {
        val result = repository.getAll().map { it.toResponse() }
        return ResponseEntity.ok(result)
    }

}