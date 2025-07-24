package com.visteon.vfin.currency.application

import com.visteon.vfin.currency.model.SystemCurrency
import java.time.Instant

data class SystemCurrencyResponse (
    val currency: String,
    val validFrom: String,
    val validTo: String?,
    val lastUpdatedAt: Instant,
    val lastUpdatedBy:String
)

fun SystemCurrency.toResponse(): SystemCurrencyResponse = SystemCurrencyResponse (
    currency=this.code.code,
    validFrom=this.validFrom.toString(),
    validTo=this.validTo.toString(),
    lastUpdatedBy = this.audit.lastUpdatedBy.value.toString(),
    lastUpdatedAt = this.audit.lastUpdatedAt
)
