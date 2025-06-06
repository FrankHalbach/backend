package com.visteon.vfin.common.types

import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@JvmInline
value class DomainDateTime(val value: LocalDateTime) : Comparable<DomainDateTime> {
    override fun toString(): String = value.format(formatter)
    override fun compareTo(other: DomainDateTime): Int = value.compareTo(other.value)

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        fun utcNow(clock: Clock = Clock.systemUTC()) =
            DomainDateTime(LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS))

        fun from(dateTime: LocalDateTime, truncate: Boolean = true): DomainDateTime =
            if (truncate) DomainDateTime(dateTime.truncatedTo(ChronoUnit.SECONDS))
            else DomainDateTime(dateTime)

        fun fromString(str: String): DomainDateTime =
            DomainDateTime(LocalDateTime.parse(str, formatter).truncatedTo(ChronoUnit.SECONDS))
    }

    fun toLocalDateTime() = value

}

fun LocalDateTime.toDomainDateTime(truncate: Boolean = true) = DomainDateTime.from(this, truncate)
