package com.visteon.vfin.exchangerate

import com.visteon.vfin.exchangerate.model.ExchangeRateVersion

interface ExchangeRateApi {

    fun getByVersions(): Set<ExchangeRateVersion>
    fun getByVersionId(id: ExchangeRateVersionId): ExchangeRateVersion?

}