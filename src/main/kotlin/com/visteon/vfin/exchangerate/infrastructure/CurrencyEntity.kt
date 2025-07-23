package com.visteon.vfin.exchangerate.infrastructure

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.javatime.date

// CURRENCY TABLE
object CurrencyEntity : Table("CURRENCY") {
    val code = varchar("CODE", 3).uniqueIndex() // ISO 4217 code
    val validFrom = date("VALID_FROM")
    val validTo = date("VALID_TO").nullable()

}