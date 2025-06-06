package com.visteon.vfin.controller

import com.visteon.vfin.project.application.CreateProjectRequest
import com.visteon.vfin.project.application.ProjectResponse
import com.visteon.vfin.project.application.ProjectService
import com.visteon.vfin.project.application.toResponse
import com.visteon.vfin.project.model.Project
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



@RestController
@RequestMapping("/api/projects")
class ProjectController(
    private val service : ProjectService,
) {

    @PostMapping
    fun createProject(@Valid @RequestBody req: CreateProjectRequest): ResponseEntity<ProjectResponse> {
        val model = Project.new(req.name)
        val project = service.create(model)
        return  ResponseEntity.ok(project.toResponse())
    }

    @PutMapping("/{id}")
    fun updateProject(
        @PathVariable id: String,
        @Valid @RequestBody req: CreateProjectRequest
    ): ResponseEntity<ProjectResponse> {
        val model = Project.from(id,req.name)
        val updated = service.update( model)
        return ResponseEntity.ok(updated.toResponse())
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: String): ResponseEntity<ProjectResponse> =
        service.getById(ProjectId.from(id))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllPlants(): ResponseEntity<List<ProjectResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result.map { it.toResponse() })
    }

}