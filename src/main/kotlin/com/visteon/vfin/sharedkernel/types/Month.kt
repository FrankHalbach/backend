package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

/**
 * A value class representing a calendar month between [MONTH_MIN] and [MONTH_MAX].
 *
 * @property value The month as an integer (1-12).
 * @throws IllegalArgumentException if the month is out of bounds.
 */
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
        /** Minimum allowed month (inclusive). */
        const val MONTH_MIN = 1
        /** Maximum allowed month (inclusive). */
        const val MONTH_MAX = 12
    }
}
