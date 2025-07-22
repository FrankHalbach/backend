package com.visteon.vfin.sharedkernel.types

import kotlin.test.*

class YearMonthTests {

    @Test
    fun `should throw exception for invalid year value in YearMonth`() {
        assertFailsWith<IllegalArgumentException> {
            YearMonth.fromString("1000-01")
        }
    }

    @Test
    fun `should parse and return correct Year and Month`() {
        val ym = YearMonth.fromString("2025-01")

        assertEquals(Year(2025), ym.year)
        assertEquals(Month(1), ym.month)
    }

    @Test
    fun `should create YearMonth from ints`() {
        val ym = YearMonth.fromParts(2025, 1)
        assertEquals(Year(2025), ym.year)
        assertEquals(Month(1), ym.month)
    }

    @Test
    fun `should create YearMonth from int`() {
        val ym = YearMonth.fromInt(202501)
        assertEquals(Year(2025), ym.year)
        assertEquals(Month(1), ym.month)
    }

    @Test
    fun `should throw on invalid formats`() {
        val invalidInputs = listOf("2024/01", "24-01", "abcd-ef", "")
        for (input in invalidInputs) {
            assertFailsWith<IllegalArgumentException> {
                YearMonth.fromString(input)
            }
        }
    }

    @Test
    fun `should compare YearMonths correctly`() {
        val jan2024 = YearMonth.fromParts(2024, 1)
        val feb2024 = YearMonth.fromParts(2024, 2)
        val jan2025 = YearMonth.fromParts(2025, 1)

        assertTrue(jan2024 < feb2024)
        assertTrue(feb2024 > jan2024)
        assertTrue(jan2024 <= jan2024)
        assertTrue(jan2025 >= jan2024)
    }
}
