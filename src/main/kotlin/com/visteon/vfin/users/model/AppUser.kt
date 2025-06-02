package com.visteon.vfin.users.model

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.common.types.EmailAddress

data class AppUser(
    val id : UserId,
    val userId: AppUserId,
    val firstName: NameField,
    val lastName: NameField,
    val email: EmailAddress
) {
    companion object {
        fun from(id: String, userId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                id = UserId.from(id),
                userId = AppUserId.Companion(userId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email)
            )

        fun new(userId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                id = UserId.new(),
                userId = AppUserId.Companion(userId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email)
            )
    }
}