package com.visteon.vfin.currency.application

import com.visteon.vfin.currency.Currency
import java.util.Locale

data class CurrencyResponse (
    val code: String,
    val displayName: String,
    val symbol: String)

fun Currency.toResponse(locale: Locale = Locale.US): CurrencyResponse {
    val javaCurrency = java.util.Currency.getInstance(this.code)
    return CurrencyResponse(
        code = this.code,
        displayName = javaCurrency.getDisplayName(locale),  // uses built-in localized display name
        symbol = javaCurrency.getSymbol(locale)
    )
}