package com.visteon.vfin.common.types

import com.visteon.vfin.common.validation.assertNotEmpty
import com.visteon.vfin.common.validation.assertLength

@JvmInline
value class NameField private constructor(val value: String) {
    companion object {
        const val NAME = "NameField"
        const val MIN_LENGTH = 1
        const val MAX_LENGTH = 50

        operator fun invoke(input: String): NameField {
            val trimmed = input.trim()
                .assertNotEmpty(NAME)
                .assertLength(MIN_LENGTH, MAX_LENGTH, NAME)
            return NameField(trimmed)
        }
    }
    override fun toString() = value
}