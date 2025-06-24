@file:NamedInterface
package com.visteon.vfin.sharedkernel.types

import com.visteon.vfin.sharedkernel.validation.assertNotEmpty
import com.visteon.vfin.sharedkernel.validation.assertLength
import com.visteon.vfin.sharedkernel.validation.assertMatchesRegex

import org.springframework.modulith.NamedInterface;

@JvmInline
@NamedInterface
value class EmailAddress private constructor(val value: String) {
    @NamedInterface
    companion object {
        const val NAME = "Email"
        const val MAX_LENGTH = 254
        val REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        operator fun invoke(input: String): EmailAddress {
            val validated = input
                .trim()
                .assertNotEmpty(NAME)
                .assertLength(5, MAX_LENGTH, NAME)
                .assertMatchesRegex(REGEX, NAME)
            return EmailAddress(validated)
        }
    }
    override fun toString() = value
}
