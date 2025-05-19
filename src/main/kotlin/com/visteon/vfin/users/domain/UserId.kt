package com.visteon.vfin.users.domain

@JvmInline
value class UserId private constructor(val value: String) {
    companion object {
        const val MIN_LENGTH = 1
        const val MAX_LENGTH = 16
        val REGEX = Regex("^[a-zA-Z0-9]+$")

        operator fun invoke(input: String): UserId {
            val normalized = input.trim().lowercase()
            require(normalized.length in MIN_LENGTH..MAX_LENGTH) {
                "UserId must be $MIN_LENGTH to $MAX_LENGTH characters."
            }
            require(REGEX.matches(normalized)) {
                "UserId must match regex: ${REGEX.pattern}"
            }
            return UserId(normalized)
        }
    }
    override fun toString() = value
}