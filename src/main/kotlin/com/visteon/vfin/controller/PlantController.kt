package com.visteon.vfin.controller

import com.visteon.vfin.plant.application.CreatePlantRequest
import com.visteon.vfin.plant.application.PlantCreatedResponse
import com.visteon.vfin.plant.application.PlantResponse
import com.visteon.vfin.plant.application.PlantService
import com.visteon.vfin.plant.application.UpdatePlantRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/plants")
class PlantController(
    private val service : PlantService
) {

    @PostMapping
    fun createPlant(@Valid @RequestBody req: CreatePlantRequest): ResponseEntity<PlantCreatedResponse> {

        val id = service.create(req)
        return  ResponseEntity.ok(PlantCreatedResponse(id.value.toString()))
    }

    @PutMapping("/{plantId}")
    fun updatePlant(
        @PathVariable plantId: UUID,
        @Valid @RequestBody req: UpdatePlantRequest
    ): ResponseEntity<Unit> {
        service.update(plantId, req)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{plantId}")
    fun getPlant(@PathVariable plantId: UUID): ResponseEntity<PlantResponse> =
        service.getById(plantId)
            ?. let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result)
    }

}