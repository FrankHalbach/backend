package com.visteon.vfin.exchangerate

import com.visteon.vfin.exchangerate.infrastructure.CurrencyEntity
import com.visteon.vfin.exchangerate.infrastructure.ExchangeRateEntity
import com.visteon.vfin.exchangerate.infrastructure.ExchangeRateVersionEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object ExchangeRateSchema {
    fun initialize() {
        SchemaUtils.create(CurrencyEntity, ExchangeRateEntity, ExchangeRateVersionEntity)
    }
}