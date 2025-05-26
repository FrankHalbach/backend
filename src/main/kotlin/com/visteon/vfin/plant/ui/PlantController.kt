package com.visteon.vfin.plant.ui

import com.visteon.vfin.plant.application.*
import com.visteon.vfin.plant.domain.CreatePlant
import com.visteon.vfin.plant.domain.PlantId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plants")
class PlantController(
    private val createPlantCommandHandler: CreatePlantCommandHandler,
    private val updatePlantCommandHandler: UpdatePlantCommandHandler,
    private val getAllPlantsQueryHandler: GetAllPlantsQueryHandler,
    private val getPlantByPlantIdQueryHandler: GetPlantsByPlantIdQueryHandler
) {

    @PostMapping
    fun createPlant(@Valid @RequestBody command: CreatePlantCommand): ResponseEntity<PlantResponse> {
        val newPlant= CreatePlant.from(code = command.code, name = command.name)
        val plant = createPlantCommandHandler.handle(command)
        return  ResponseEntity.ok(plant.toResponse())
    }

    @PutMapping("/{plantId}")
    fun updatePlant(
        @PathVariable plantId: Int,
        @Valid @RequestBody body: UpdatePlantCommand
    ): ResponseEntity<PlantResponse> {
        val updated = updatePlantCommandHandler.handle(plantId,body)
        return ResponseEntity.ok(updated.toResponse())
    }

    @GetMapping("/{plantId}")
    fun getPlant(@PathVariable plantId: Int): ResponseEntity<PlantResponse> =
        getPlantByPlantIdQueryHandler.handle(GetPlantByPlantIdQuery(PlantId(plantId)))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<PlantResponse>> {
        val result = getAllPlantsQueryHandler.handle().map { it.toResponse() }
        return ResponseEntity.ok(result)
    }

}