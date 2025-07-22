package com.visteon.vfin.sharedkernel.types

import kotlin.test.*

class YearTests {

    @Test
    fun `should throw exception for invalid year`() {
        assertFailsWith<IllegalArgumentException> {
            Year(1999)
        }

    }

    @Test
    fun `should compare years correctly`() {
        val y1 = Year(2020)
        val y2 = Year(2021)

        assertTrue(y2 > y1)
        assertTrue(y1 < y2)
        assertEquals(0, y1.compareTo(y1))
    }

    @Test
    fun `should convert year to string`() {
        assertEquals("2024", Year(2024).toString())
    }
}
