package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

/**
 * A domain-specific representation of a year and month, similar to `java.time.YearMonth`,
 * but using custom [Year] and [Month] types to stay fully in domain control.
 *
 * Backed by two integer components: [year] and [month], and convertible to/from
 * a compact integer (e.g., `202507` for July 2025).
 *
 * Supports parsing from `"YYYY-MM"` format, and is validated via regex.
 *
 * Can be used safely in SQL using [toInt] and [fromInt], e.g., for storing as an `Int` column.
 *
 * Example:
 * ```
 * val ym = YearMonth.fromString("2025-07")
 * val intValue = ym.toInt()         // 202507
 * val parsed = YearMonth.fromInt(202507)
 * ```
 *
 * @property year The year component.
 * @property month The month component (1–12).
 * @throws IllegalArgumentException if constructed from invalid values.
 */
@NamedInterface
data class YearMonth(val year: Year, val month: Month) : Comparable<YearMonth> {

    /**
     * Converts the year-month pair to a compact integer: `year * 100 + month`.
     * Useful for efficient storage or sorting.
     */
    fun toInt(): Int = year.value * 100 + month.value

    @NamedInterface
    companion object {
        /** Regex string for validating ISO-style YearMonth (e.g. "2024-09"). */
        const val REGEX_STRING = "^\\d{4}-(0[1-9]|1[0-2])$"

        /** Compiled regex for fast validation of input strings. */
        val YEAR_MONTH_REGEX = Regex(REGEX_STRING)

        /**
         * Creates a [YearMonth] from individual year and month values.
         *
         * @param year The 4-digit year (e.g. 2025)
         * @param month The 1-based month (1–12)
         * @throws IllegalArgumentException if either part is invalid.
         */
        fun fromParts(year: Int, month: Int): YearMonth =
            YearMonth(Year(year), Month(month))

        /**
         * Parses a compact Int (e.g., `202507`) into a [YearMonth].
         *
         * @param value the integer value in `YYYYMM` format
         * @return a valid [YearMonth] instance
         */
        fun fromInt(value: Int): YearMonth =
            YearMonth(Year(value / 100), Month(value % 100))

        /**
         * Parses a string in `YYYY-MM` format into a [YearMonth].
         *
         * @param input the input string, e.g., "2025-07"
         * @throws IllegalArgumentException if the format is invalid.
         */
        fun fromString(input: String): YearMonth {
            require(YEAR_MONTH_REGEX.matches(input)) {
                "Invalid YearMonth format: $input. Expected format: YYYY-MM"
            }

            val yearPart = input.substring(0, 4).toInt()
            val monthPart = input.substring(5, 7).toInt()

            return fromParts(yearPart, monthPart)
        }
    }

    /**
     * Compares by year first, then by month.
     */
    override fun compareTo(other: YearMonth): Int =
        year.compareTo(other.year).takeIf { it != 0 }
            ?: month.compareTo(other.month)


    /**
     * Renders the year-month as a `YYYY-MM` string.
     */
    override fun toString(): String = "$year-$month"



}
