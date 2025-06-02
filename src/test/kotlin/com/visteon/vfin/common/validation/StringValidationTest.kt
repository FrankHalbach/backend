package com.visteon.vfin.common.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


internal class StringValidationTest {

    @Test
    fun `assertNotEmpty returnsString whenNotEmpty`() {
        val result = "hello".assertNotEmpty("TestField")
        assertEquals("hello", result)
    }

    @Test
    fun `assertNotEmpty throws whenEmpty`() {
        val exception = assertThrows<IllegalArgumentException> {
            "".assertNotEmpty("TestField")
        }
        assertEquals("TestField must not be empty.", exception.message)
    }

    @Test
    fun `assertLength returnsString whenWithinBounds`() {
        val result = "hello".assertLength(3, 6, "TestField")
        assertEquals("hello", result)
    }

    @Test
    fun `assertLength throws whenTooShort`() {
        val exception = assertThrows<IllegalArgumentException> {
            "hi".assertLength(3, 6, "TestField")
        }
        assertEquals("TestField must be between 3 and 6 characters.", exception.message)
    }

    @Test
    fun `assertLength throws whenTooLong`() {
        val exception = assertThrows<IllegalArgumentException> {
            "toolong".assertLength(3, 6, "TestField")
        }
        assertEquals("TestField must be between 3 and 6 characters.", exception.message)
    }

    @Test
    fun `assertMatchesRegex returnsString whenMatch`() {
        val result = "abc123".assertMatchesRegex(Regex("[a-z]+\\d+"), "TestField")
        assertEquals("abc123", result)
    }

    @Test
    fun `assertMatchesRegex throws whenNoMatch`() {
        val exception = assertThrows<IllegalArgumentException> {
            "123abc".assertMatchesRegex(Regex("[a-z]+\\d+"), "TestField")
        }
        assertEquals("TestField must match pattern: [a-z]+\\d+", exception.message)
    }
}