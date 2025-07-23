package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.exchangerate.model.ExchangeRateVersion


interface ExchangeRateCommandService {

    // todo: 1) add currency to currency table, 2) edit currency in currency table


    fun createDraft(req: CreateExchangeRateVersionRequest): ExchangeRateVersion
//    fun release(versionId: ExchangeRateVersionId, by: UserId): ExchangeRateVersion
//    fun updateDetails(versionId: ExchangeRateVersionId, req: UpdateExchangeRateVersionRequest): ExchangeRateVersion
    // maybe: fun upsertRates(...)
}