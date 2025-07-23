package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.exchangerate.model.ExchangeRateVersion
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ExchangeRateCommandServiceImpl() : ExchangeRateCommandService {
    override fun createDraft(req: CreateExchangeRateVersionRequest): ExchangeRateVersion {
        TODO("Not yet implemented")
    }

}