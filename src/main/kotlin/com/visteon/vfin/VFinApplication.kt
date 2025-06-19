package com.visteon.vfin

import org.jetbrains.exposed.v1.spring.boot.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import com.visteon.vfin.users.UserId
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import java.util.UUID

@SpringBootApplication
@ImportAutoConfiguration(
    value = [ExposedAutoConfiguration::class],
    //exclude = [DataSourceTransactionManagerAutoConfiguration::class]
)
class VFinApplication

fun main(args: Array<String>) {
    runApplication<VFinApplication>(*args)
}




interface UserContext {
    fun currentUserId(): UserId
}

@Component
class DummyUserContext : UserContext {
    override fun currentUserId(): UserId =
        UserId(UUID.fromString("018fa4d6-8fd3-7ccf-92e6-4cb0538e5c3b")) // or UUIDv7, whatever you use
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