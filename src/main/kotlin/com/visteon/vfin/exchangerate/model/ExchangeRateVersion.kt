package com.visteon.vfin.exchangerate.model

import com.visteon.vfin.currency.Currency
import com.visteon.vfin.exchangerate.ExchangeRateVersionId
import com.visteon.vfin.sharedkernel.types.AuditInfo
import java.time.Instant

data class ExchangeRateVersion(
    val id: ExchangeRateVersionId,
    val versionName: String,
    val description: String,
    val consolidationCurrency: Currency,
    val status: ExchangeRateVersionStatus,
    val auditInfo: AuditInfo,
    val releasedAt: Instant?
)


//move to service

//fun ExchangeRateVersion.createDraft(
//    versionName: String,
//    consolidationCurrency: Currency,
//    createdBy: UserId): ExchangeRateVersion {
//    return ExchangeRateVersion(
//        id = ExchangeRateVersionId.new(),
//        versionName = versionName,
//        consolidationCurrency = consolidationCurrency,
//        status = ExchangeRateVersionStatus.DRAFT,
//        auditInfo = AuditInfo.create(createdBy)
//    )
//}
//
//
//fun ExchangeRateVersion.release(by: UserId): ExchangeRateVersion {
//    require(this.status == ExchangeRateVersionStatus.DRAFT) {
//        "Only DRAFT versions can be released."
//    }
//
//    return this.copy(
//        status = ExchangeRateVersionStatus.RELEASED,
//        auditInfo = this.auditInfo.updated(by)
//    )
//}
//
//fun ExchangeRateVersion.updateDetails(
//    newVersionName: String,
//    newConsolidationCurrency: Currency,
//    updatedBy: UserId
//): ExchangeRateVersion {
//    require(this.status == ExchangeRateVersionStatus.DRAFT) {
//        "Cannot update details of a released version."
//    }
//
//    return this.copy(
//        versionName = newVersionName,
//        consolidationCurrency = newConsolidationCurrency,
//        auditInfo = this.auditInfo.updated(updatedBy)
//    )
//}