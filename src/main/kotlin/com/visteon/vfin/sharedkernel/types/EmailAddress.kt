@file:NamedInterface
package com.visteon.vfin.sharedkernel.types

import com.visteon.vfin.sharedkernel.validation.assertNotEmpty
import com.visteon.vfin.sharedkernel.validation.assertMatchesRegex
import com.visteon.vfin.sharedkernel.validation.assertMaxLength
import com.visteon.vfin.sharedkernel.validation.assertMinLength

import org.springframework.modulith.NamedInterface

@JvmInline
@NamedInterface
value class EmailAddress private constructor(val value: String) {
    @NamedInterface
    companion object {
        const val NAME = "Email"
        const val MIN_LENGTH = 14  // ab@visteon.com
        const val MAX_LENGTH = 255
        val REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

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
