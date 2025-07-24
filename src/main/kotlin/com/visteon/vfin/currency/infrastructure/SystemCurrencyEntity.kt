package com.visteon.vfin.currency.infrastructure

import com.visteon.vfin.currency.Currency
import com.visteon.vfin.currency.model.SystemCurrency
import com.visteon.vfin.sharedkernel.exposedtypes.nullableYearMonthInt
import com.visteon.vfin.sharedkernel.exposedtypes.yearMonthInt
import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.AuditInfo
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.javatime.timestamp


// CURRENCY TABLE
object SystemCurrencyEntity : Table("SYSTEM_CURRENCY") {
    val code = varchar("CODE", 3).uniqueIndex() // ISO 4217 code
    val validFrom = yearMonthInt("VALID_FROM")
    val validTo = nullableYearMonthInt("VALID_TO")
    val createdAt = timestamp("CREATED_AT")
    val createdBy = uuid("CREATED_BY")
    val modifiedAt = timestamp("MODIFIED_AT").nullable()
    val modifiedBy = uuid("MODIFIED_BY").nullable()
}


fun ResultRow.toSystemCurrency(): SystemCurrency = SystemCurrency(
    Currency(this[SystemCurrencyEntity.code]),
    this[SystemCurrencyEntity.validFrom],
    this[SystemCurrencyEntity.validTo],
    AuditInfo(
        this[SystemCurrencyEntity.createdAt],
        UserId(this[SystemCurrencyEntity.createdBy]),
        this[SystemCurrencyEntity.modifiedAt],
        this[SystemCurrencyEntity.modifiedBy]?.let { UserId(it) })
)

