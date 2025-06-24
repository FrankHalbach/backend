package com.visteon.vfin.plant.model

import com.visteon.vfin.common.Ids
import com.visteon.vfin.sharedkernel.types.AuditInfo
import com.visteon.vfin.sharedkernel.identifiers.UserId
import java.time.Instant
import java.util.UUID

data class Plant(
    val plantId: PlantId,
    val code: String,
    val name: String,
    val audit: AuditInfo
) {
    fun update(code: String, name: String, updatedBy: UserId): Plant =
        this.copy(
            code = code,
            name = name,
            audit = audit.updated(updatedBy)
        )


    companion object {
        fun from(plantId: UUID, code:String, name:String, createdAt:Instant, createdBy:UserId, modifiedAt:Instant?, modifiedBy:UserId?): Plant =
            Plant(
                plantId = PlantId(plantId),
                code = code,
                name = name,
                audit = AuditInfo(createdAt = createdAt, createdBy = createdBy, modifiedAt = modifiedAt, modifiedBy = modifiedBy)
            )

        fun new(code:String, name:String, createdBy:UserId): Plant =
            Plant(PlantId(Ids.new()),code,name,AuditInfo.create(createdBy))

    }
}

