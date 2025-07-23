package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.sharedkernel.validation.ValidYearMonth
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


// use for create and update
data class CurrencyRequest(

    @field:NotBlank
    @field:Size(min = 3, max = 3)
    val code: String,

    @field:ValidYearMonth
    val validFrom: String,


    @field:ValidYearMonth
    val validTo: String?
)
