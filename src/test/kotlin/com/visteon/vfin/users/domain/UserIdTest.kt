package com.visteon.vfin.users.domain

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class UserIdTest {

    @Test
    fun `should create UserId with trimmed and lowercase input`() {
        val userId = UserId(" AbC123 ")
        assertEquals("abc123", userId.value)
        assertEquals("abc123", userId.toString())
    }

    @Test
    fun `should throw exception for empty input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            UserId("")
        }
        assertEquals("UserId must not be empty.", exception.message)
    }

    @Test
    fun `should throw exception for invalid pattern`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            UserId("invalid_id!")
        }
        assertEquals(
            "UserId must match pattern: ${UserId.REGEX.pattern}",
            exception.message
        )
    }
}