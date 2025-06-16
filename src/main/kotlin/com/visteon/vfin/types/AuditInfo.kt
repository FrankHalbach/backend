//package com.visteon.vfin.common.types
//
//import com.visteon.vfin.users.model.UserId
//import java.time.Instant
//
//data class AuditInfo(
//    val created: Instant,
//    val createdBy: UserId,
//    val modified: Instant? = null,
//    val modifiedBy: UserId? = null
//) {
//    val lastUpdated get() = modified ?: created
//    val lastUpdatedBy get() = modifiedBy ?: createdBy
//}


