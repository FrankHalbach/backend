package com.visteon.vfin.sharedkernel.identifiers

import com.visteon.vfin.common.Ids
import java.util.UUID
import org.springframework.modulith.NamedInterface;

@NamedInterface
@JvmInline
value class UserId(val value: UUID) {
    @NamedInterface
    companion object {
        fun new() = UserId(Ids.new())
        fun from(id:String) = UserId(UUID.fromString(id))
    }
}