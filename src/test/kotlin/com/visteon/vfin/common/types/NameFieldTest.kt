package com.visteon.vfin.common.types

import com.visteon.vfin.types.NameField
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

internal class NameFieldTest {

    @Test
    fun `should create NameField with trimmed valid input`() {
        val name = NameField(" John ")
        assertEquals("John", name.value)
        assertEquals("John", name.toString())
    }

    @Test
    fun `should throw exception for empty input`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            NameField("")
        }
        assertEquals("NameField must not be empty.", exception.message)
    }

    @Test
    fun `should throw exception for too long input`() {
        val longName = "a".repeat(NameField.MAX_LENGTH + 1)
        val exception = assertFailsWith<IllegalArgumentException> {
            NameField(longName)
        }
        assertEquals(
            "NameField must be between ${NameField.MIN_LENGTH} and ${NameField.MAX_LENGTH} characters.",
            exception.message
        )
    }
}