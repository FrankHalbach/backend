package com.visteon.vfin.common.types

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class EmailAddressTest {

    @Test
    fun `should create EmailAddress with valid input`() {
        val email = EmailAddress(" user@example.com ")
        assertEquals("user@example.com", email.value)
        assertEquals("user@example.com", email.toString())
    }

    @Test
    fun `should throw exception for empty input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            EmailAddress("")
        }
        assertEquals("Email must not be empty.", exception.message)
    }

    @Test
    fun `should throw exception for too short input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            EmailAddress("a@b")
        }
        assertEquals(
            "Email must be between 5 and ${EmailAddress.MAX_LENGTH} characters.",
            exception.message
        )
    }

    @Test
    fun `should throw exception for invalid format`() {
        val invalid = "invalid-email.com"
        val exception = assertFailsWith<IllegalArgumentException> {
            EmailAddress(invalid)
        }
        assertEquals(
            "Email must match pattern: ${EmailAddress.REGEX.pattern}",
            exception.message
        )
    }

    @Test
    fun `should throw exception for too long input`() {
        val longLocal = "a".repeat(EmailAddress.MAX_LENGTH - "@e.com".length + 1)
        val longEmail = "$longLocal@e.com"
        val exception = assertFailsWith<IllegalArgumentException> {
            EmailAddress(longEmail)
        }
        assertEquals(
            "Email must be between 5 and ${EmailAddress.MAX_LENGTH} characters.",
            exception.message
        )
    }
}