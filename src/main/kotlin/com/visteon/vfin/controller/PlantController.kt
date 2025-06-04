package com.visteon.vfin.controller

import com.visteon.vfin.plant.application.CreatePlantRequest
import com.visteon.vfin.plant.application.PlantResponse
import com.visteon.vfin.plant.application.PlantService
import com.visteon.vfin.plant.application.UpdatePlantRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plants")
class PlantController(
    private val service : PlantService
) {

    @PostMapping
    fun createPlant(@Valid @RequestBody req: CreatePlantRequest): ResponseEntity<PlantResponse> {

        val plant = service.create(req)
        return  ResponseEntity.ok(plant)
    }

    @PutMapping("/{plantId}")
    fun updatePlant(
        @PathVariable plantId: Int,
        @Valid @RequestBody req: UpdatePlantRequest
    ): ResponseEntity<PlantResponse> {
        val updated = service.update(plantId, req)
        return ResponseEntity.ok(updated)
    }

    @GetMapping("/{plantId}")
    fun getPlant(@PathVariable plantId: Int): ResponseEntity<PlantResponse> =
        service.getById(plantId)
            ?. let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result)
    }

}