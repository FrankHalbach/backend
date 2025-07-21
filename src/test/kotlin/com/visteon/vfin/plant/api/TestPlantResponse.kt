package com.visteon.vfin.plant.api

import java.time.Instant

data class TestPlantResponse(
    val id: String,
    val code: String,
    val name: String,
    val lastUpdatedBy: String,
    val lastUpdatedAt: Instant
)