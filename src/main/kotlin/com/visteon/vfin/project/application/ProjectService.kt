package com.visteon.vfin.project.application

import com.visteon.vfin.common.exception.DuplicateEntityException
import com.visteon.vfin.common.exception.EntityNotFoundException
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
    fun create(request: ProjectCreationRequest): Project {

        if(repo.projectNumberExists(request.projectNumber))
            throw DuplicateEntityException("Project", "Project Number", request.projectNumber)

        val newProject = Project.new(request.projectNumber,request.projectTitle)

        repo.create(newProject)

        return newProject
    }
    fun update(projectId: ProjectId, request: ProjectUpdateRequest): Project {

        val current = repo.getById(projectId) ?: throw EntityNotFoundException("Project",projectId.value.toString())

        if(repo.projectNumberExistsForOtherProjects(projectId, request.projectNumber))
            throw DuplicateEntityException("Project","Project Number", request.projectNumber)

        val updated = current.update(request.projectNumber,request.projectTitle)

        repo.update(updated)

        return updated
    }

    fun getAll():List<Project> = repo.getAll()
    fun getById(id:ProjectId): Project? = repo.getById(id)

}