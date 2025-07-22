package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

@JvmInline
@NamedInterface
value class Year(val value: Int) : Comparable<Year> {
    init {
        require(value in YEAR_MIN..YEAR_MAX) {
            "Year must be between $YEAR_MIN and $YEAR_MAX"
        }
    }

    override fun compareTo(other: Year): Int = value.compareTo(other.value)

    override fun toString(): String = value.toString()

    companion object {
        const val YEAR_MIN = 2000
        const val YEAR_MAX = 2999
    }
}
