package com.visteon.vfin.exchangerate.model

import com.visteon.vfin.exchangerate.Currency
import com.visteon.vfin.sharedkernel.types.Money
import com.visteon.vfin.sharedkernel.types.YearMonth

data class ExchangeRate(
    val from: Currency,
    val to: Currency,
    val yearMonth: YearMonth,
    val rateValue: Money
) {
    init {
        require(from != to) { "Cannot create exchange rate for the same currency." }
        require(rateValue > Money(0.0)) { "Exchange rate must be positive." }
    }

    fun inverse(): ExchangeRate = ExchangeRate(
        from = to,
        to = from,
        yearMonth = yearMonth,
        rateValue = Money(1.0) / ( rateValue)
    )
}