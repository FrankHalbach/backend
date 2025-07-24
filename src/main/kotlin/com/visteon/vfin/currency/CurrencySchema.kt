package com.visteon.vfin.currency

import com.visteon.vfin.currency.infrastructure.SystemCurrencyEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils

object CurrencySchema {
    fun initialize() {
        SchemaUtils.create(SystemCurrencyEntity)
    }
}

