package com.visteon.vfin.sharedkernel.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoneyTests {

    @Test
    fun `should create Money from Double and String`() {
        val m1 = Money(123.45)
        val m2 = Money("123.45")
        assertEquals(m1, m2)
        assertEquals("123.45", m1.toString())
    }

    @Test
    fun `should fail for negative amounts`() {
        assertThrows<IllegalArgumentException> {
            Money(-0.01)
        }
        assertThrows<IllegalArgumentException> {
            Money("-0.01")
        }
    }

    @Test
    fun `should fail for scale greater than 2`() {
        assertThrows<IllegalArgumentException> {
            Money("123.456")
        }
    }

    @Test
    fun `add and subtract Money correctly`() {
        val a = Money("10.00")
        val b = Money("5.50")
        assertEquals(Money("15.50"), a+b)
        assertEquals(Money("4.50"), a-b)
    }

    @Test
    fun `multiply and divide Money correctly`() {
        val a = Money("10.00")
        val factor = Money("2.5")
        assertEquals(Money("25.00"), a * factor)
        assertEquals(Money("4.00"), a / Money("2.5"))
    }

    @Test
    fun `compareTo and comparison operators work`() {
        val a = Money("10.00")
        val b = Money("20.00")
        assertTrue(a < b)
        assertTrue(b > a)
        assertTrue(a <= b)
        assertTrue(b >= a)
        assertTrue(a == Money("10.00"))
    }
}
