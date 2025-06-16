package com.visteon.vfin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules

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
