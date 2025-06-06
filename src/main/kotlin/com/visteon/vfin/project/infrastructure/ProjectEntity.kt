package com.visteon.vfin.project.infrastructure

import com.visteon.vfin.common.Ids
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*


@Table(name = "project")
@Entity
data class ProjectEntity(
    @Id
    val id: UUID = Ids.empty(),
    val name: String = ""
)