package com.visteon.vfin.controller

import com.visteon.vfin.plant.application.PlantCreationRequest
import com.visteon.vfin.plant.application.PlantCreationResponse
import com.visteon.vfin.plant.application.PlantResponse
import com.visteon.vfin.plant.application.PlantService
import com.visteon.vfin.plant.application.PlantUpdateRequest
import com.visteon.vfin.plant.model.PlantId
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
    fun createPlant(@Valid @RequestBody req: PlantCreationRequest): ResponseEntity<PlantCreationResponse> {
        val id = service.create(req)
        return  ResponseEntity.ok(PlantCreationResponse(id.value.toString()))
    }

    @PutMapping("/{id}")
    fun updatePlant(
        @PathVariable id: UUID,
        @Valid @RequestBody req: PlantUpdateRequest
    ): ResponseEntity<Unit> {
        service.update(PlantId(id), req)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getPlant(@PathVariable id: UUID): ResponseEntity<PlantResponse> =
        service.getById(PlantId(id))
            ?. let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result)
    }

}