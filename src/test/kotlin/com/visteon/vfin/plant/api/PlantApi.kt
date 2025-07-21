package com.visteon.vfin.plant.api

import com.visteon.vfin.Helpers
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec
import java.net.URI
import java.util.UUID

@Lazy // To ensure local.server.port is set by the time it is wired
@Component
class PlantApi {

    @Value("\${local.server.port}")
    private var port: Long = 0

    private val plantPath = "/api/plants"

    fun uriForPlantId(id: UUID): URI = URI.create(Helpers.baseUri(port) + plantPath + "/" + id)


    fun createPlant(plantRequest: TestPlantCreationRequest): ResponseSpec =
        Helpers.newWebClient(port.toLong())
            .post()
            .uri(plantPath)
            .bodyValue(plantRequest)
            .exchange()


    fun createPlantAsEntity(plantRequest: TestPlantCreationRequest): TestPlantResponse =
        getPlantFromResponse(createPlant(plantRequest))


    fun getPlant(id: UUID): ResponseSpec =
        getPlant(uriForPlantId(id))


    fun getPlant(plantUri: URI): ResponseSpec {
        return Helpers.newWebClient(port.toLong())
            .get()
            .uri(plantUri)
            .exchange()
    }

    fun getAllPlants(): ResponseSpec =
        Helpers
            .newWebClient(port.toLong())
            .get()
            .uri(plantPath)
            .exchange()


    fun updatePlant(id: UUID, plantUpdateRequest: TestPlantUpdateRequest): ResponseSpec =
        Helpers.newWebClient(port.toLong())
            .put()
            .uri(uriForPlantId(id))
            .bodyValue(plantUpdateRequest)
            .exchange()


    fun getPlantFromResponse(response: ResponseSpec): TestPlantResponse =
        response
            .expectBody(TestPlantResponse::class.java)
            .returnResult()
            .responseBody!!
}
