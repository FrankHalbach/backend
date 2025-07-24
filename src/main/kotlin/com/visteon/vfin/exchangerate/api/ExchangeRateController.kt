package com.visteon.vfin.exchangerate.api

import com.visteon.vfin.exchangerate.ExchangeRateVersionId
import com.visteon.vfin.exchangerate.application.ExchangeRateService
import com.visteon.vfin.exchangerate.application.ExchangeRateVersionRequest
import com.visteon.vfin.exchangerate.application.ExchangeRateVersionResponse
import com.visteon.vfin.exchangerate.application.toResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/exchangeRates")
class ExchangeRateController(
    private val service: ExchangeRateService,
) {


    @PostMapping
    fun createDraftVersion(@Valid @RequestBody req: ExchangeRateVersionRequest):ResponseEntity<ExchangeRateVersionResponse> =
        ResponseEntity.ok(service.createDraft(req).toResponse())

    @PutMapping("/{id}")
    fun updateDraftVersion(
        @PathVariable id: UUID,
        @Valid @RequestBody req: ExchangeRateVersionRequest): ResponseEntity<ExchangeRateVersionResponse> =
        ResponseEntity.ok(service.updateDraft(ExchangeRateVersionId(id),req).toResponse())


    //todo: release version

    @GetMapping
    fun getExchangeRateVersions(): ResponseEntity<Set<ExchangeRateVersionResponse>> =
        ResponseEntity.ok(service.getByVersions()
            .map { it.toResponse()}
            .toSet())

    @GetMapping("/{id}")
    fun getExchangeRateVersion(@PathVariable id: UUID): ResponseEntity<ExchangeRateVersionResponse> =
        service.getByVersionId(ExchangeRateVersionId(id))
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

}