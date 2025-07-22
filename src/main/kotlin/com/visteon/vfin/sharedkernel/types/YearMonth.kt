package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface

@NamedInterface
data class YearMonth(val year: Year, val month: Month) : Comparable<YearMonth> {

    fun toInt(): Int = year.value * 100 + month.value

    companion object {
        private val YearMonthRegex = Regex("""^\d{4}-\d{2}$""")

        fun fromParts(year: Int, month: Int): YearMonth =
            YearMonth(Year(year), Month(month))

        fun fromInt(value: Int): YearMonth =
            YearMonth(Year(value / 100), Month(value % 100))

        fun fromString(input: String): YearMonth {
            require(YearMonthRegex.matches(input)) {
                "Invalid YearMonth format: $input. Expected format: YYYY-MM"
            }

            val yearPart = input.substring(0, 4).toInt()
            val monthPart = input.substring(5, 7).toInt()

            return fromParts(yearPart, monthPart)
        }
    }

    override fun compareTo(other: YearMonth): Int =
        year.compareTo(other.year).takeIf { it != 0 }
            ?: month.compareTo(other.month)

    override fun toString(): String = "$year-$month"



}
