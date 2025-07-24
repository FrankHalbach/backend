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
    /**
     * Returns the last update timestamp, falling back to creation time if never modified.
     */
   val lastUpdatedAt get() = modifiedAt ?: createdAt

    /**
     * Returns the last user who updated the entity, falling back to creator if never modified.
     */
   val lastUpdatedBy get() = modifiedBy ?: createdBy

    /**
     * Returns a copy of this [AuditInfo] with updated modification timestamp and user.
     *
     * @param userId The user performing the update.
     * @return A new [AuditInfo] instance reflecting the update.
     */
    fun updated(userId: UserId): AuditInfo =
        this.copy(
            modifiedAt = Instant.now(),            
            modifiedBy = userId
        )
   @NamedInterface
   companion object {

       /**
        * Creates a new [AuditInfo] instance representing a creation event.
        *
        * @param userId The user creating the entity.
        * @return A new [AuditInfo] with current timestamp and creator user.
        */
        fun create(userId: UserId): AuditInfo =
            AuditInfo(
                createdAt = Instant.now(),
                createdBy = userId
            )
        }       

}
