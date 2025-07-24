package com.visteon.vfin.exchangerate.infrastructure

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.currency.Currency
import com.visteon.vfin.exchangerate.ExchangeRateVersionId
import com.visteon.vfin.exchangerate.model.ExchangeRateVersion
import com.visteon.vfin.exchangerate.model.ExchangeRateVersionStatus
import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.AuditInfo
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp


// EXCHANGE RATE VERSION TABLE
object ExchangeRateVersionEntity : UUIDTable("EXCHANGE_RATE_VERSION") {
    val versionName = varchar("VERSION_NAME", 32).uniqueIndex()
    val description = varchar("DESCRIPTION", 1000)
    val status = enumerationByName("STATUS", FieldLengths.ENUM, ExchangeRateVersionStatus::class)
    val consolidationCurrencyCode = varchar("CONSOLIDATION_CURRENCY_CODE", 3)
    val createdAt = timestamp("CREATED_AT")
    val createdBy = uuid("CREATED_BY")
    val modifiedAt = timestamp("MODIFIED_AT").nullable()
    val modifiedBy = uuid("MODIFIED_BY").nullable()
    val releasedAt = timestamp("RELEASED_AT").nullable()
}

fun ResultRow.toExchangeRateVersion(): ExchangeRateVersion = ExchangeRateVersion(
    ExchangeRateVersionId(this[ExchangeRateVersionEntity.id].value),
    this[ExchangeRateVersionEntity.versionName],
    this[ExchangeRateVersionEntity.description],
    Currency(this[ExchangeRateVersionEntity.consolidationCurrencyCode]),
    this[ExchangeRateVersionEntity.status],
    AuditInfo(
        this[ExchangeRateVersionEntity.createdAt],
        UserId(this[ExchangeRateVersionEntity.createdBy]),
        this[ExchangeRateVersionEntity.modifiedAt],
        this[ExchangeRateVersionEntity.modifiedBy]?.let { UserId(it) }),
    this[ExchangeRateVersionEntity.releasedAt]

)
