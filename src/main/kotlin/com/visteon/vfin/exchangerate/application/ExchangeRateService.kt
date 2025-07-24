package com.visteon.vfin.exchangerate.application

import com.visteon.vfin.UserContext
import com.visteon.vfin.currency.Currency
import com.visteon.vfin.exchangerate.ExchangeRateApi
import com.visteon.vfin.exchangerate.ExchangeRateVersionId
import com.visteon.vfin.exchangerate.infrastructure.ExchangeRateVersionEntity
import com.visteon.vfin.exchangerate.infrastructure.toExchangeRateVersion
import com.visteon.vfin.exchangerate.model.ExchangeRateVersion
import com.visteon.vfin.exchangerate.model.ExchangeRateVersionStatus
import com.visteon.vfin.sharedkernel.types.AuditInfo
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ExchangeRateService(
    private val userContext: UserContext
    ) : ExchangeRateApi {

    fun createDraft(req: ExchangeRateVersionRequest): ExchangeRateVersion {

        val newDraft = ExchangeRateVersion(
            id = ExchangeRateVersionId.new(),
            versionName = req.versionName,
            description = req.description,
            consolidationCurrency = Currency("USD"),     // TODO: put into const central configuration file
            status = ExchangeRateVersionStatus.DRAFT,
            auditInfo = AuditInfo.create(userContext.currentUserId()),
            releasedAt = null)

        ExchangeRateVersionEntity.insert {
            it[id] = newDraft.id.value
            it[versionName] = newDraft.versionName
            it[description] = newDraft.description
            it[consolidationCurrencyCode] = newDraft.consolidationCurrency.code
            it[status] = newDraft.status
            it[createdAt] = newDraft.auditInfo.createdAt
            it[createdBy] = newDraft.auditInfo.createdBy.value
        }

        return newDraft
    }

    fun updateDraft(versionId: ExchangeRateVersionId, req: ExchangeRateVersionRequest): ExchangeRateVersion {
        TODO("Not yet implemented")
    }

    //fun release(versionId: ExchangeRateVersionId, by: UserId): ExchangeRateVersion

    override fun getByVersions(): Set<ExchangeRateVersion> =
        ExchangeRateVersionEntity
            .selectAll()
            .map { it.toExchangeRateVersion() }
            .toSet()

    override fun getByVersionId(id: ExchangeRateVersionId): ExchangeRateVersion? =
        ExchangeRateVersionEntity
            .selectAll()
            .where { ExchangeRateVersionEntity.id eq id.value}
            .singleOrNull()
            ?.toExchangeRateVersion()




}