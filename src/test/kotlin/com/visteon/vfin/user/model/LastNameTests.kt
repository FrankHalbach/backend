package com.visteon.vfin.user.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LastNameTests {

    @Test
    fun `should trim and retain valid name`() {
        val name = LastName("  Müller  ")
        assertEquals("Müller", name.value)
    }

    @Test
    fun `should throw on empty input`() {
        val exception = assertThrows<IllegalArgumentException> {
            LastName("   ") // becomes empty after trim
        }
        assertEquals("Last Name must not be empty.", exception.message)
    }


    @Test
    fun `should throw if longer than max length`() {
        val longName = "A".repeat(256)
        val exception = assertThrows<IllegalArgumentException> {
            LastName(longName)
        }
        assertEquals("Last Name must be at most 255 characters.", exception.message)
    }

}
