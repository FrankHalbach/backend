package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

@JvmInline
@NamedInterface
value class Month(val value: Int) : Comparable<Month> {
    init {
        require(value in MONTH_MIN..MONTH_MAX) {
            "Month must be between $MONTH_MIN and $MONTH_MAX"
        }
    }

    override fun compareTo(other: Month): Int = value.compareTo(other.value)

    override fun toString(): String = "%02d".format(value)

    companion object {
        const val MONTH_MIN = 1
        const val MONTH_MAX = 12
    }
}
