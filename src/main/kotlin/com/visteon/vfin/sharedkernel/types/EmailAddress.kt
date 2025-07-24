@file:NamedInterface
package com.visteon.vfin.sharedkernel.types

import com.visteon.vfin.sharedkernel.validation.assertNotEmpty
import com.visteon.vfin.sharedkernel.validation.assertMatchesRegex
import com.visteon.vfin.sharedkernel.validation.assertMaxLength
import com.visteon.vfin.sharedkernel.validation.assertMinLength

import org.springframework.modulith.NamedInterface


/**
 * A value class representing a validated email address.
 *
 * Enforces:
 * - Minimum length of [MIN_LENGTH] characters.
 * - Maximum length of [MAX_LENGTH] characters.
 * - Matches the regular expression pattern for valid email addresses ([REGEX]).
 *
 * Use the [invoke] operator to construct an instance with validation:
 * ```
 * val email = EmailAddress("user@example.com")
 * ```
 *
 * @property value The validated email address string.
 * @throws IllegalArgumentException if validation fails.
 */
@JvmInline
@NamedInterface
value class EmailAddress private constructor(val value: String) {
    @NamedInterface
    companion object {
        /** Friendly name for error messages */
        const val NAME = "Email"
        /** Minimum allowed length of the email address (inclusive). */
        const val MIN_LENGTH = 14  // ab@visteon.com
        /** Maximum allowed length of the email address (inclusive). */
        const val MAX_LENGTH = 255
        /** Regex pattern to validate the email format. */
        val REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")


        /**
         * Creates a validated [EmailAddress] from a raw string.
         *
         * Trims the input and applies all validations.
         *
         * @throws IllegalArgumentException if the input fails any validation.
         */
        operator fun invoke(input: String): EmailAddress {
            val validated = input
                .trim()
                .assertNotEmpty(NAME)
                .assertMinLength(MIN_LENGTH,NAME)
                .assertMaxLength( MAX_LENGTH, NAME)
                .assertMatchesRegex(REGEX, NAME)
            return EmailAddress(validated)
        }
    }
    override fun toString() = value
}
