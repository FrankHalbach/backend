package com.visteon.vfin.common.types

import com.visteon.vfin.users.model.UserId

data class AuditInfo(
    val created: DomainDateTime,
    val createdBy: UserId,
    val modified: DomainDateTime? = null,
    val modifiedBy: UserId? = null
) {
    val lastUpdated get() = modified ?: created
    val lastUpdatedBy get() = modifiedBy ?: createdBy
}


