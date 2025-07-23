package com.visteon.vfin.exchangerate.infrastructure

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.timestamp


// EXCHANGE RATE VERSION TABLE
object ExchangeRateVersionEntity : UUIDTable("EXCHANGE_RATE_VERSION") {
    val versionName = varchar("VERSION_NAME", 32).uniqueIndex()
    val description = varchar("DESCRIPTION", 1000)
    val status = varchar("STATUS", 255)
    val consolidationCurrencyCode = varchar("CONSOLIDATION_CURRENCY_CODE", 3).references(CurrencyEntity.code)
    val createdAt = timestamp("CREATED_AT")
    val createdBy = uuid("CREATED_BY")
    val modifiedAt = timestamp("MODIFIED_AT").nullable()
    val modifiedBy = uuid("MODIFIED_BY").nullable()
    val releasedAt = timestamp("RELEASED_AT").nullable()
}

