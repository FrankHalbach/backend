package com.visteon.vfin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class VFinApplication

fun main(args: Array<String>) {
    runApplication<VFinApplication>(*args)
}
