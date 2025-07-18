package com.visteon.vfin.users.model

import com.visteon.vfin.sharedkernel.validation.assertMaxLength
import com.visteon.vfin.sharedkernel.validation.assertNotEmpty

@JvmInline
value class LastName private constructor(val value: String) {
    companion object {
        const val MAX_LENGTH = 255
        const val FIELD_NAME = "Last Name"
        operator fun invoke(input: String): LastName {
            val validated = input
                .trim()
                .assertNotEmpty(FIELD_NAME)
                .assertMaxLength(MAX_LENGTH, FIELD_NAME)
            return LastName(validated)
        }
    }
    override fun toString() = value
}