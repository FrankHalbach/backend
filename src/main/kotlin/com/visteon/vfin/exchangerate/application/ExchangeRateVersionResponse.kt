package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.exchangerate.model.ExchangeRateVersion
import com.visteon.vfin.exchangerate.model.ExchangeRateVersionStatus
import java.time.Instant
import java.util.UUID


data class ExchangeRateVersionResponse(
    val id: UUID,
    val versionName:String,
    val description:String,
    val consolidationCurrency:String,
    val status: ExchangeRateVersionStatus,
    val lastUpdatedBy:String,
    val lastUpdatedAt: Instant
)

fun ExchangeRateVersion.toResponse(): ExchangeRateVersionResponse= ExchangeRateVersionResponse(
    id = this.id.value,
    versionName = this.versionName,
    description = this.description,
    consolidationCurrency = this.consolidationCurrency.toString(),
    status = this.status,
    lastUpdatedBy = this.auditInfo.lastUpdatedBy.value.toString(),
    lastUpdatedAt = this.auditInfo.lastUpdatedAt
)
