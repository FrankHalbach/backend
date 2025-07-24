package com.visteon.vfin.exchangerate

import com.visteon.vfin.currency.Currency
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CurrencyTest {

    @Test
    fun `should create Currency with supported code`() {
        val currency = Currency("USD")
        assertEquals("USD", currency.code)
    }

    @Test
    fun `should create Currency case insensitively`() {
        val currency = Currency.from("eur")
        assertEquals("EUR", currency.code)
    }

    @Test
    fun `should throw exception for unsupported currency code`() {
        val exception = assertThrows<IllegalArgumentException> {
            Currency("XYZ")
        }
        assertTrue(exception.message!!.contains("Unsupported currency code"))
    }

    @Test
    fun `isSupported should return true for supported currency`() {
        assertTrue(Currency.isSupported("USD"))
        assertTrue(Currency.isSupported("eur"))
    }

    @Test
    fun `isSupported should return false for unsupported currency`() {
        assertFalse(Currency.isSupported("ABC"))
    }

    @Test
    fun `toString should return code`() {
        val currency = Currency("JPY")
        assertEquals("JPY", currency.toString())
    }
}
