package com.visteon.vfin.exchangerate

import com.visteon.vfin.exchangerate.model.ExchangeRateVersion
import com.visteon.vfin.exchangerate.model.ExchangeRateVersionId

interface ExchangeRateQueryService {
    fun getByVersionId(id: ExchangeRateVersionId): ExchangeRateVersion? //replace by version with rates
}