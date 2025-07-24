package com.visteon.vfin.exchangerate.model

import com.visteon.vfin.currency.Currency
import com.visteon.vfin.sharedkernel.types.Money
import com.visteon.vfin.sharedkernel.types.YearMonth
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ExchangeRateTests {
    @Test
    fun `should create valid ExchangeRate`() {
        val rate = ExchangeRate(
            from = Currency.from("USD"),
            to = Currency.from("EUR"),
            yearMonth = YearMonth.fromParts(2024,7),
            rateValue = Money("1.25")
        )
        assertEquals("USD", rate.from.code)
        assertEquals("EUR", rate.to.code)

        assertEquals(rate.copy(
            from=rate.to,
            to=rate.from,
            rateValue = Money(1.0)/Money(1.25)), rate.inverse())

        assertEquals(Money("1.25"), rate.rateValue)
    }
}