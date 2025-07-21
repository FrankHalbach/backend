package com.visteon.vfin

import org.jetbrains.exposed.v1.spring.boot.autoconfigure.ExposedAutoConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.modulith.core.ApplicationModules
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class BackendApplicationTests {

    @Test
    fun contextLoads() {


    }

    @Test
    fun modules(){

        val modules = ApplicationModules.of(VFinApplication::class.java)
        modules.forEach { println(it) }

        ApplicationModules.of(VFinApplication::class.java).verify()

    }


}
