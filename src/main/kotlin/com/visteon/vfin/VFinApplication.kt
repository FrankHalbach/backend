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
        UserId(UUID.fromString("00000000-0000-0000-0000-000000000001")) // or UUIDv7, whatever you use
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