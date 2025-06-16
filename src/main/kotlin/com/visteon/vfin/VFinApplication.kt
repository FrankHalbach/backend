package com.visteon.vfin

import org.jetbrains.exposed.v1.spring.boot.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


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