package com.visteon.vfin.exchangerate

import com.visteon.vfin.common.Ids
import java.util.UUID

data class ExchangeRateVersionId(val value: UUID) {
    companion object {
        fun new() = ExchangeRateVersionId(Ids.new())
        fun from(value: String) = ExchangeRateVersionId(UUID.fromString(value))

    }
}