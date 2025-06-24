package com.visteon.vfin.users.model

import com.visteon.vfin.sharedkernel.validation.assertMatchesRegex
import com.visteon.vfin.sharedkernel.validation.assertNotEmpty

@JvmInline
value class AppUserId private constructor(val value: String) {
    companion object {
        const val NAME = "UserId"
        const val REGEX_PATTERN = "^[a-zA-Z0-9]{1,16}$"
        const val VALIDATION_MESSAGE = "UserId must be 1–16 alphanumeric characters (a–z, A–Z, 0–9)."

        val REGEX = Regex(this.REGEX_PATTERN)

        operator fun invoke(input: String): AppUserId {
            val normalized = input.trim().lowercase()
                .assertNotEmpty(NAME)
                .assertMatchesRegex(REGEX, NAME)

            return AppUserId(normalized)
        }
    }
    override fun toString() = value
}

