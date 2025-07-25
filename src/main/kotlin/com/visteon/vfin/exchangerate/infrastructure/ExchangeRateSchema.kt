package com.visteon.vfin.exchangerate.infrastructure

import com.visteon.vfin.configuration.InitDev
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.stereotype.Component


@Component
internal class ExchangeRateSchema {

    @InitDev()
    fun createSchema() {
        SchemaUtils.create(
            ExchangeRateVersionEntity,
            ExchangeRateEntity
        )
    }

    @InitDev()
    fun seedData() {
        // Your seed data
    }
}