package com.visteon.vfin.plant.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.assertj.core.api.Assertions.assertThat
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlantTests {

    @Autowired
    private lateinit var plantApi: PlantApi

    @Test
    fun givenIWantToCreateAPlant_WhenICreate() {
        val plantRequest = TestPlantCreationRequest("TestPlant", "Test Location")

        val response = plantApi.createPlant(plantRequest)

        itShouldCreateANewPlant(response)
        val newPlant = plantApi.getPlantFromResponse(response)
        itShouldAllocateANewId(newPlant)
        itShouldConfirmPlantDetails(plantRequest, newPlant)
    }

    private fun itShouldCreateANewPlant(response: ResponseSpec) {
        response
            .expectStatus()
            .isOk()
    }

    private fun itShouldAllocateANewId(newPlant: TestPlantResponse) {
        assertThat(newPlant.id).isNotEmpty()
        assertThat(newPlant.id).isNotNull()
    }

    private fun itShouldConfirmPlantDetails(plantRequest: TestPlantCreationRequest, newPlant: TestPlantResponse) {
        assertThat(newPlant.name).isEqualTo(plantRequest.name)
        assertThat(newPlant.code).isEqualTo(plantRequest.code)
        assertThat(newPlant.lastUpdatedBy).isNotNull()
        assertThat(newPlant.lastUpdatedAt).isNotNull()
    }



    private fun itShouldFindTheNewPlant(response: ResponseSpec) {
        response
            .expectStatus()
            .isOk()
    }

    private fun itShouldNotFindThePlant(response: ResponseSpec) {
        response
            .expectStatus()
            .isNotFound()
    }




    private fun itShouldUpdateThePlant(response: ResponseSpec) {
        response
            .expectStatus()
            .isOk()
    }

    private fun itShouldConfirmUpdatedPlantDetails(updateRequest: TestPlantUpdateRequest, updatedPlant: TestPlantResponse) {
        assertThat(updatedPlant.name).isEqualTo(updateRequest.name)
        // Assuming location maps to code; adjust if needed
        assertThat(updatedPlant.code).isEqualTo(updateRequest.location)
        assertThat(updatedPlant.lastUpdatedBy).isNotNull()
        assertThat(updatedPlant.lastUpdatedAt).isNotNull()
    }

    @Test
    fun givenIWantToGetAllPlants_WhenIRequestAll() {
        val plantRequest1 = TestPlantCreationRequest("Plant1", "Location 1")
        val plantRequest2 = TestPlantCreationRequest("Plant2", "Location 2")
        plantApi.createPlantAsEntity(plantRequest1)
        plantApi.createPlantAsEntity(plantRequest2)

        val response = plantApi.getAllPlants()

        itShouldReturnAllPlants(response)
    }

    private fun itShouldReturnAllPlants(response: ResponseSpec) {
        response
            .expectStatus()
            .isOk()
            .expectBodyList(TestPlantResponse::class.java).hasSize(2)
    }
}