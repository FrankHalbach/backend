package com.visteon.vfin

import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.spring.boot.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@SpringBootApplication
@ImportAutoConfiguration(
    value = [ExposedAutoConfiguration::class],
    //exclude = [DataSourceTransactionManagerAutoConfiguration::class]
)
class VFinApplication

fun main(args: Array<String>) {
    runApplication<VFinApplication>(*args)
}


//@Configuration
//@ImportAutoConfiguration(
//    value = [ExposedAutoConfiguration::class],
//    exclude = [DataSourceTransactionManagerAutoConfiguration::class]
//)
//class ExposedConfig {
//    @Bean
//    fun databaseConfig() = DatabaseConfig {
//        useNestedTransactions = true
//    }
//}