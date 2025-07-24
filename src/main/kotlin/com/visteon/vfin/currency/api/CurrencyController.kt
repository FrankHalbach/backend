package com.visteon.vfin.currency.api

import com.visteon.vfin.currency.Currency
import com.visteon.vfin.currency.application.*
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/currencies")
class CurrencyController(private val currencyService: CurrencyService) {

    // Currencies

    @Operation(
        summary="List of all world currencies",
        description = "Use as input for `CurrencyRequest.code` only!. Use `getSupportedCurrency` for system currencies.")
    @GetMapping("/world")
    fun getWorldCurrencies() : ResponseEntity<List<CurrencyResponse>> =
        ResponseEntity.ok(Currency.worldCurrencies.map { it.toResponse() })

    @Operation(summary = "List of system supported currencies")
    @GetMapping()
    fun getCurrencies(): ResponseEntity<Set<SystemCurrencyResponse>> =
        ResponseEntity.ok(currencyService.getSystemCurrencies().map { it.toResponse()}.toSet())

    @PostMapping()
    fun upsertCurrency(@Valid @RequestBody req: SystemCurrencyRequest): ResponseEntity<SystemCurrencyResponse> =
        ResponseEntity.ok(currencyService.upsertCurrency(req).toResponse())

}