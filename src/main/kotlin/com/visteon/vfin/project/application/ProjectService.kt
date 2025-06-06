package com.visteon.vfin.project.application

import com.visteon.vfin.common.crud.GenericBaseService
import com.visteon.vfin.project.infrastructure.ProjectRepository
import com.visteon.vfin.project.model.Project
import com.visteon.vfin.project.model.ProjectId
import org.springframework.stereotype.Service

@Service
class ProjectService(private val repo: ProjectRepository) : GenericBaseService<Project, ProjectId>(repo)