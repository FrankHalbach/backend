package com.visteon.vfin.users.model

import com.visteon.vfin.common.Ids
import java.util.UUID

@JvmInline
value class UserId(val value: UUID) {
    companion object {
        fun new() = UserId(Ids.new())
        fun from(id:String) = UserId(UUID.fromString(id))
    }
}