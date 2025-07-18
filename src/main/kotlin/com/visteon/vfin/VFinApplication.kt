package com.visteon.vfin

import org.jetbrains.exposed.v1.spring.boot.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import com.visteon.vfin.sharedkernel.identifiers.UserId
import org.springframework.stereotype.Component
import java.util.UUID

@SpringBootApplication
@ImportAutoConfiguration(ExposedAutoConfiguration::class)
class VFinApplication

fun main(args: Array<String>) {
    runApplication<VFinApplication>(*args)
}



//move / delete after we have user context via spring
interface UserContext {
    fun currentUserId(): UserId
}

@Component
class DummyUserContext : UserContext {
    override fun currentUserId(): UserId =
        UserId(UUID.fromString("018fa4d6-8fd3-7ccf-92e6-4cb0538e5c3b")) // or UUIDv7, whatever you use
}
