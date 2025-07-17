package com.visteon.vfin.plant.api

import com.visteon.vfin.plant.application.PlantCreationRequest
import com.visteon.vfin.plant.application.PlantResponse
import com.visteon.vfin.plant.application.PlantService
import com.visteon.vfin.plant.application.PlantUpdateRequest
import com.visteon.vfin.plant.application.toResponse
import com.visteon.vfin.plant.model.PlantId
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/plants")
class PlantController(private val service: PlantService) {

    @PostMapping
    fun createPlant(@Valid @RequestBody req: PlantCreationRequest): ResponseEntity<PlantResponse> =
            ResponseEntity.ok(service.create(req).toResponse())

    @PutMapping("/{id}")
    fun updatePlant(
            @PathVariable id: UUID,
            @Valid @RequestBody req: PlantUpdateRequest
    ): ResponseEntity<PlantResponse> =
            ResponseEntity.ok(service.update(PlantId(id), req).toResponse())

    @GetMapping("/{id}")
    fun getPlant(@PathVariable id: UUID): ResponseEntity<PlantResponse> =
            service.getById(PlantId(id))?.let { ResponseEntity.ok(it.toResponse()) }
                    ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> =
            ResponseEntity.ok(service.getAll().map { it.toResponse() })
}
