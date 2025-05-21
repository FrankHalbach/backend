package com.visteon.vfin.users.domain

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.common.types.EmailAddress

data class User(
    val userId: UserId,
    val firstName: NameField,
    val lastName: NameField,
    val email: EmailAddress
) {
    companion object {
        fun from(userId: String, firstName: String, lastName: String, email: String): User =
            User(
                userId = UserId.Companion(userId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email)
            )
    }
}