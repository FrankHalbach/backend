package com.visteon.vfin.currency.model

import com.visteon.vfin.currency.Currency
import com.visteon.vfin.sharedkernel.types.AuditInfo
import com.visteon.vfin.sharedkernel.types.YearMonth

data class SystemCurrency(
    val code: Currency,
    val validFrom: YearMonth,
    val validTo: YearMonth?,
    val audit : AuditInfo
)