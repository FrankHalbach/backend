package com.visteon.vfin.user.model

import com.visteon.vfin.sharedkernel.validation.assertMaxLength
import com.visteon.vfin.sharedkernel.validation.assertNotEmpty

@JvmInline
value class FirstName private constructor(val value: String) {
    companion object {
        const val MAX_LENGTH = 255
        const val FIELD_NAME = "First Name"
        operator fun invoke(input: String): FirstName {
            val validated = input
                .trim()
                .assertNotEmpty(FIELD_NAME)
                .assertMaxLength(MAX_LENGTH, FIELD_NAME)
            return FirstName(validated)
        }
    }

    override fun toString() = value
}
