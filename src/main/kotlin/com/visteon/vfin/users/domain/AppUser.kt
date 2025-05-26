package com.visteon.vfin.users.domain

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.common.types.EmailAddress

data class AppUser(
    val userId: AppUserId,
    val firstName: NameField,
    val lastName: NameField,
    val email: EmailAddress
) {
    companion object {
        fun from(userId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                userId = AppUserId.Companion(userId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email)
            )
    }
}