package com.visteon.vfin

import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration


class Helpers {
    fun baseUri(port: Long): String = "http://localhost:$port"

    fun newWebClient(port: Long): WebTestClient {
        return WebTestClient
            .bindToServer()
            .baseUrl(baseUri(port))
            .responseTimeout(Duration.ofSeconds(90))
            .build()
    }
}