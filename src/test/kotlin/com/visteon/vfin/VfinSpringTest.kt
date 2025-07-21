package com.visteon.vfin

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/*
    Decorate Api test with this annotation class
*/

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
annotation class VfinSpringTest