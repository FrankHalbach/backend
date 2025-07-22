package com.visteon.vfin.plant.api

import com.visteon.vfin.VfinSpringTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec
import org.assertj.core.api.Assertions.assertThat
import java.util.UUID

@VfinSpringTest
class PlantTests {

    @Autowired
    private lateinit var plantApi: PlantApi

    @Test
    fun `given I want to create a plant, when I create`() {
        val plantRequest = TestPlantCreationRequest("TestPlant", "Test Location")

        val response = plantApi.createPlant(plantRequest)

        `it should create a new plant`(response)
        val newPlant = plantApi.getPlantFromResponse(response)
        `it should allocate a new id`(newPlant)
        `it should confirm plant details`(plantRequest, newPlant)
    }

    @Test
    fun `given a non-existing plant, when I get request it`() {
        val nonExistingId = UUID.randomUUID()

        val response = plantApi.getPlant(nonExistingId)

        `it should not find the plant`(response)
    }

    @Test
    fun `given an invalid plant name, when I create`() {
        val request = TestPlantCreationRequest("ValidCode", "")

        val response = plantApi.createPlant(request)

        `it should reject the request with bad request`(response)
    }

    @Test
    fun `given I want to update a plant, when I update`() {
        val original = TestPlantCreationRequest("Code123", "Initial Name")
        val created = plantApi.createPlantAsEntity(original)
        val id = UUID.fromString(created.id)

        val updateRequest = TestPlantUpdateRequest("Updated Name", "Updated Location")
        val response = plantApi.updatePlant(id, updateRequest)

        `it should update the plant`(response)

        val updated = plantApi.getPlantFromResponse(plantApi.getPlant(id))
        `it should confirm updated plant details`(updateRequest, updated)
    }

    @Test
    fun `given a non-existing plant, when I update`() {
        val nonExistingId = UUID.randomUUID()
        val updateRequest = TestPlantUpdateRequest("New Name", "New Location")

        val response = plantApi.updatePlant(nonExistingId, updateRequest)

        `it should not find the plant`(response)
    }

    @Test
    fun `given I want to get all plants, when I request all`() {
        val plantRequest1 = TestPlantCreationRequest("Plant1", "Location 1")
        val plantRequest2 = TestPlantCreationRequest("Plant2", "Location 2")
        plantApi.createPlantAsEntity(plantRequest1)
        plantApi.createPlantAsEntity(plantRequest2)

        val response = plantApi.getAllPlants()

        `it should return all plants`(response)
    }

    // 🔽 Shared expectations

    private fun `it should create a new plant`(response: ResponseSpec) =
        response
            .expectStatus()
            .isOk()


    private fun `it should allocate a new id`(newPlant: TestPlantResponse) {
        assertThat(newPlant.id).isNotEmpty()
        assertThat(newPlant.id).isNotNull()
    }

    private fun `it should confirm plant details`(plantRequest: TestPlantCreationRequest, newPlant: TestPlantResponse) {
        assertThat(newPlant.name).isEqualTo(plantRequest.name)
        assertThat(newPlant.code).isEqualTo(plantRequest.code)
        assertThat(newPlant.lastUpdatedBy).isNotNull()
        assertThat(newPlant.lastUpdatedAt).isNotNull()
    }

    private fun `it should find the new plant`(response: ResponseSpec) =
        response
            .expectStatus()
            .isOk()


    private fun `it should not find the plant`(response: ResponseSpec) =
        response
            .expectStatus()
            .isNotFound()


    private fun `it should reject the request with bad request`(response: ResponseSpec) =
        response
            .expectStatus()
            .isBadRequest


    private fun `it should update the plant`(response: ResponseSpec) =
        response
            .expectStatus()
            .isOk()


    private fun `it should confirm updated plant details`(updateRequest: TestPlantUpdateRequest, updatedPlant: TestPlantResponse) {
        assertThat(updatedPlant.name).isEqualTo(updateRequest.name)
        // Assuming location maps to code in your DTO model
        assertThat(updatedPlant.code).isEqualTo(updateRequest.code)
        assertThat(updatedPlant.lastUpdatedBy).isNotNull()
        assertThat(updatedPlant.lastUpdatedAt).isNotNull()
    }

    private fun `it should return all plants`(response: ResponseSpec) {
        val result = response
            .expectStatus().isOk
            .expectBodyList(TestPlantResponse::class.java)
            .returnResult()

        val plants = result.responseBody ?: emptyList()
        assertThat(plants).hasSizeGreaterThanOrEqualTo(2)
    }

}
