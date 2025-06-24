package com.visteon.vfin.sharedkernel.types

import com.visteon.vfin.sharedkernel.identifiers.UserId
import java.time.Instant
import org.springframework.modulith.NamedInterface;

@NamedInterface
data class AuditInfo(
   val createdAt: Instant,
   val createdBy: UserId,
   val modifiedAt: Instant? = null,
   val modifiedBy: UserId? = null
) {
   val lastUpdatedAt get() = modifiedAt ?: createdAt
   val lastUpdatedBy get() = modifiedBy ?: createdBy
   
   fun updated(userId: UserId): AuditInfo =
        this.copy(
            modifiedAt = Instant.now(),            
            modifiedBy = userId
        )
   @NamedInterface
   companion object {
        fun create(userId: UserId): AuditInfo =
            AuditInfo(
                createdAt = Instant.now(),
                createdBy = userId
            )
        }       

}
