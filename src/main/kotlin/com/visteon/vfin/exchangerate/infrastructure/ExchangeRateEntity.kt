package com.visteon.vfin.exchangerate.infrastructure

import org.jetbrains.exposed.v1.core.Table

// EXCHANGE RATE TABLE
object ExchangeRateEntity : Table("EXCHANGE_RATE") {
    val versionId = uuid("VERSION_ID").references(ExchangeRateVersionEntity.id)
    val currencyFrom = varchar("CURRENCY_FROM", 3).references(CurrencyEntity.code)
    val currencyTo = varchar("CURRENCY_TO", 3).references(CurrencyEntity.code)
    val yearMonth = integer("YEAR_MONTH")  // Or NUMBER(6), here as Int for YYYYMM
    val rateValue = decimal("RATE_VALUE", precision = 20, scale = 10)

    override val primaryKey = PrimaryKey(versionId, currencyFrom, currencyTo, yearMonth, name = "PK_EXCHANGE_RATE")
}