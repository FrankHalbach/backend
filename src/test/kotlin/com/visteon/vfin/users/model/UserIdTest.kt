package com.visteon.vfin.users.model

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class UserIdTest {

    @Test
    fun `should create UserId with trimmed and lowercase input`() {
        val userId = AppUserId(" AbC123 ")
        assertEquals("abc123", userId.value)
        assertEquals("abc123", userId.toString())
    }

    @Test
    fun `should throw exception for empty input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            AppUserId("")
        }
        assertEquals("UserId must not be empty.", exception.message)
    }

    @Test
    fun `should throw exception for invalid pattern`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            AppUserId("invalid_id!")
        }
        assertEquals(
            "UserId must match pattern: ${AppUserId.REGEX.pattern}",
            exception.message
        )
    }
}