package com.visteon.vfin.project.api

import com.visteon.vfin.project.application.ProjectService
import com.visteon.vfin.project.application.ProjectCreationRequest
import com.visteon.vfin.project.application.ProjectResponse
import com.visteon.vfin.project.application.ProjectUpdateRequest
import com.visteon.vfin.project.application.toResponse
import com.visteon.vfin.project.model.ProjectId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/projects")
class ProjectController(
    private val service : ProjectService,
) {


    @PostMapping
    fun createProject(@Valid @RequestBody req: ProjectCreationRequest): ResponseEntity<ProjectResponse> =
        ResponseEntity.ok(service.create(req).toResponse())


    @PutMapping("/{id}")
    fun updateProject(
        @PathVariable id: UUID,
        @Valid @RequestBody req: ProjectUpdateRequest
    ): ResponseEntity<ProjectResponse> =
        ResponseEntity.ok(service.update(ProjectId(id), req).toResponse())



    @GetMapping("/{id}")
    fun getProject(@PathVariable id: UUID): ResponseEntity<ProjectResponse> =
        service.getById(ProjectId(id))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<ProjectResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result.map { it.toResponse() })
    }

}