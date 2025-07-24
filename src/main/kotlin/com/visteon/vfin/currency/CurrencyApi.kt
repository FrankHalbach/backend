package com.visteon.vfin.currency

import com.visteon.vfin.currency.model.SystemCurrency

interface CurrencyApi {
    fun getSystemCurrencies(): Set<SystemCurrency>

}