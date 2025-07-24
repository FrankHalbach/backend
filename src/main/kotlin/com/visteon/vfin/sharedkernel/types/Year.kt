package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

/**
 * A value class representing a calendar year constrained between [YEAR_MIN] and [YEAR_MAX].
 *
 * Designed to restrict years to the range 2000–2999 to fit domain requirements.
 *
 * @property value The year as an integer.
 * @throws IllegalArgumentException if the year is out of bounds.
 */
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
        /** Minimum allowed year (inclusive). */
        const val YEAR_MIN = 2000
        /** Maximum allowed year (inclusive). */
        const val YEAR_MAX = 2999
        /** Regex pattern to validate a 4-digit year. */
        const val REGEX_STRING ="^\\d{4}$"
    }
}
