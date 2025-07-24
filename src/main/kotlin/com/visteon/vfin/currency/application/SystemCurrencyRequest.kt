package com.visteon.vfin.currency.application

import com.visteon.vfin.sharedkernel.validation.ValidCurrency
import com.visteon.vfin.sharedkernel.validation.ValidYearMonth
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

// use for create and update
data class SystemCurrencyRequest(

    @field:ValidCurrency
    val code: String,

    // todo: is String the right type ? can we create a type which shows in swagger as YearMonth yyyy-mm similar to html?
    @field:ValidYearMonth
    val validFrom: String,


    @field:ValidYearMonth
    val validTo: String?
)