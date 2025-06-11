package com.visteon.vfin.project.model

import com.visteon.vfin.common.Ids
import java.util.UUID

@JvmInline
value class ProjectId(val value: UUID) {
    companion object {
        fun new() = ProjectId(Ids.new())
        fun from(id:String) = ProjectId(UUID.fromString(id))
    }
}