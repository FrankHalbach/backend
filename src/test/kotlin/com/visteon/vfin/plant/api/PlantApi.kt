package com.visteon.vfin.plant.api

import com.visteon.vfin.Helpers
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec
import java.net.URI
import java.time.Instant
import java.util.UUID

@Lazy // To ensure local.server.port is set by the time it is wired
@Component
class PlantApi {

    @Value("\${local.server.port}")
    private var port: Int = 0

    private val PLANT_PATH = "/api/plants"

    fun uriForPlantId(id: UUID): URI {
        return URI.create(Helpers().baseUri(port.toLong()) + PLANT_PATH + "/" + id)
    }

    fun createPlant(plantRequest: TestPlantCreationRequest): ResponseSpec {
        return Helpers().newWebClient(port.toLong())
            .post()
            .uri(PLANT_PATH)
            .bodyValue(plantRequest)
            .exchange()
    }

    fun createPlantAsEntity(plantRequest: TestPlantCreationRequest): TestPlantResponse {
        return getPlantFromResponse(createPlant(plantRequest))
    }

    fun getPlant(id: UUID): ResponseSpec {
        return getPlant(uriForPlantId(id))
    }

    fun getPlant(plantUri: URI): ResponseSpec {
        return Helpers().newWebClient(port.toLong())
            .get()
            .uri(plantUri)
            .exchange()
    }

    fun getAllPlants(): ResponseSpec {
        return Helpers().newWebClient(port.toLong())
            .get()
            .uri(PLANT_PATH)
            .exchange()
    }

    fun updatePlant(id: UUID, plantUpdateRequest: TestPlantUpdateRequest): ResponseSpec {
        return Helpers().newWebClient(port.toLong())
            .put()
            .uri(uriForPlantId(id))
            .bodyValue(plantUpdateRequest)
            .exchange()
    }

    fun getPlantFromResponse(response: ResponseSpec): TestPlantResponse {
        return response
            .expectBody(TestPlantResponse::class.java)
            .returnResult()
            .responseBody!!
    }
}

data class TestPlantCreationRequest(
    val code: String,
    val name: String
)

data class TestPlantUpdateRequest(
    val name: String,
    val location: String
)

data class TestPlantResponse(
    val id: String,
    val code: String,
    val name: String,
    val lastUpdatedBy: String,
    val lastUpdatedAt: Instant
)

