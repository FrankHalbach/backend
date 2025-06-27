package com.visteon.vfin.sharedkernel.identifiers

import com.visteon.vfin.users.model.AppUserId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class UserIdTest {

    @Test
    fun `should create UserId with trimmed and lowercase input`() {
        val userId = AppUserId.Companion(" AbC123 ")
        Assertions.assertEquals("abc123", userId.value)
        Assertions.assertEquals("abc123", userId.toString())
    }

    @Test
    fun `should throw exception for empty input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            AppUserId.Companion("")
        }
        Assertions.assertEquals("UserId must not be empty.", exception.message)
    }

    @Test
    fun `should throw exception for invalid pattern`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            AppUserId.Companion("invalid_id!")
        }
        Assertions.assertEquals(
            "UserId must match pattern: ${AppUserId.Companion.REGEX.pattern}",
            exception.message
        )
    }
}