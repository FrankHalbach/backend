package com.visteon.vfin.currency.infrastructure

import com.visteon.vfin.configuration.InitDev
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.stereotype.Component

@Component
internal class CurrencySchema {

    @InitDev()
    fun createSchema() {
        SchemaUtils.create(SystemCurrencyEntity)
    }

    @InitDev()
    fun seedData() {
        // Your seed data
    }
}