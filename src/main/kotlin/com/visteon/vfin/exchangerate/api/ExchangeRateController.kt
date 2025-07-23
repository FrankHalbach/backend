package com.visteon.vfin.exchangerate.api

import com.visteon.vfin.exchangerate.Currency
import com.visteon.vfin.exchangerate.application.CurrencyRequest
import com.visteon.vfin.exchangerate.application.CreateExchangeRateVersionRequest
import com.visteon.vfin.exchangerate.application.CurrencyResponse
import com.visteon.vfin.exchangerate.application.ExchangeRateCommandService
import com.visteon.vfin.exchangerate.application.ExchangeRateVersionResponse
import com.visteon.vfin.exchangerate.application.toResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exchangeRates")
class ExchangeRateController(private val cmdService: ExchangeRateCommandService) {


    // Currencies

    @GetMapping("/currencies")
    fun getCurrencies() : ResponseEntity<List<CurrencyResponse>> =
        ResponseEntity.ok(Currency.supportedCurrencies.map { it.toResponse() })



    @PostMapping("/currencies")
    fun createCurrency(@Valid @RequestBody req: CurrencyRequest): ResponseEntity<CurrencyRequest> {

        return ResponseEntity.ok(req)
    }



    // Exchange Rate Version
    @PostMapping
    fun createDraft(@Valid @RequestBody req: CreateExchangeRateVersionRequest):ResponseEntity<ExchangeRateVersionResponse> =
        ResponseEntity.ok(cmdService.createDraft(req).toResponse())
}