package com.visteon.vfin.plant.model

import com.visteon.vfin.sharedkernel.types.AuditInfo

data class Plant(
    val plantId: PlantId,
    val code: String,
    val name: String,
    val audit: AuditInfo
)