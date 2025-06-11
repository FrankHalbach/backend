package com.visteon.vfin.project.application

import com.visteon.vfin.project.infrastructure.ProjectRepository
import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Transactional
@Service
class ProjectService(
    private val repo: ProjectRepository
) {
    fun create(request: CreateProjectRequest): ProjectId {
        val newProject = Project.new(request.projectNumber,request.projectTitle)
        val id = repo.create(newProject)
        return id
    }
    fun update(projectId:ProjectId, request: UpdateProjectRequest) {
        val current = repo.getById(projectId) ?: throw NoSuchElementException("Project with ID ${projectId.value} not found\"")
        val updated = current.updateFrom(request.projectNumber,request.projectTitle)
        repo.update(updated)
    }

    fun getAll():List<Project> = repo.getAll()
    fun getById(id:ProjectId): Project? = repo.getById(id)

}