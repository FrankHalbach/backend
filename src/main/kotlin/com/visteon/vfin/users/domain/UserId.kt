package com.visteon.vfin.users.domain

import com.visteon.vfin.common.validation.assertMatchesRegex
import com.visteon.vfin.common.validation.assertNotEmpty

@JvmInline
value class UserId private constructor(val value: String) {
    companion object {
        const val NAME = "UserId"
        const val REGEX_PATTERN = "^[a-zA-Z0-9]{1,16}$"
        const val VALIDATION_MESSAGE = "UserId must be 1–16 alphanumeric characters (a–z, A–Z, 0–9)."

        val REGEX = Regex(this.REGEX_PATTERN)

        operator fun invoke(input: String): UserId {
            val normalized = input.trim().lowercase()
                .assertNotEmpty(NAME)
                .assertMatchesRegex(REGEX, NAME)

            return UserId(normalized)
        }
    }
    override fun toString() = value
}