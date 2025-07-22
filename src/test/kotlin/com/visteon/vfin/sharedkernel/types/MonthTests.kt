package com.visteon.vfin.sharedkernel.types

import kotlin.test.*

class MonthTests {

    @Test
    fun `should init Month via factory method`() {
        val month = Month(5)
        assertEquals(5, month.value)
    }

    @Test
    fun `should throw exception for invalid month`() {
        assertFailsWith<IllegalArgumentException> {
            Month(13)
        }
    }

    @Test
    fun `should convert month to string with leading zero`() {
        val month = Month(3)
        assertEquals("03", month.toString())
    }

    @Test
    fun `should compare months correctly`() {
        val jan = Month(1)
        val feb = Month(2)

        assertTrue(feb > jan)
        assertTrue(jan < feb)
        assertEquals(0, jan.compareTo(jan))
    }
}
