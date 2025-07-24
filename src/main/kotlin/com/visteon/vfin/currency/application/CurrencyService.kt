package com.visteon.vfin.currency.application

import com.visteon.vfin.UserContext
import com.visteon.vfin.currency.Currency
import com.visteon.vfin.currency.CurrencyApi
import com.visteon.vfin.currency.infrastructure.SystemCurrencyEntity
import com.visteon.vfin.currency.infrastructure.toSystemCurrency
import com.visteon.vfin.currency.model.SystemCurrency
import com.visteon.vfin.sharedkernel.types.AuditInfo
import com.visteon.vfin.sharedkernel.types.YearMonth
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CurrencyService(
    val userContext: UserContext
): CurrencyApi {
    override fun getSystemCurrencies(): Set<SystemCurrency> {
        val currencies = SystemCurrencyEntity.selectAll().map { it.toSystemCurrency() }.toSet()
        return currencies
    }


    fun upsertCurrency(req: SystemCurrencyRequest) : SystemCurrency {

        val existing  = SystemCurrencyEntity
            .selectAll()
            .where { SystemCurrencyEntity.code eq req.code }
            .singleOrNull()
            ?.toSystemCurrency()


        if(existing!=null) {

            val updated = existing.copy(
                validFrom =YearMonth.fromString(req.validFrom),
                validTo = req.validTo?.let { YearMonth.fromString(req.validTo) },
                audit = existing.audit.updated(userContext.currentUserId())
            )

            SystemCurrencyEntity.update({ SystemCurrencyEntity.code eq req.code }) {
                it[validFrom] = updated.validFrom
                it[validTo] = updated.validTo
                it[modifiedBy] = updated.audit.modifiedBy?.value
                it[modifiedAt] = updated.audit.modifiedAt
            }

            return updated;

        }
        else {

            val model = SystemCurrency(
                Currency(req.code),
                YearMonth.fromString(req.validFrom),
                req.validTo?.let { YearMonth.fromString(req.validTo) },
                audit = AuditInfo.create(userContext.currentUserId())
            )

            SystemCurrencyEntity.insert {
                it[code] = model.code.code
                it[validFrom] = model.validFrom
                it[validTo] = model.validTo
                it[createdBy] = model.audit.createdBy.value
                it[createdAt] = model.audit.createdAt
            }

            return model

        }
    }
}