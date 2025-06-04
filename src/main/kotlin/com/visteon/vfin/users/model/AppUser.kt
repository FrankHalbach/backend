package com.visteon.vfin.users.model

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.common.types.EmailAddress

data class AppUser(
    val id : UserId,
    val appUserId: AppUserId,
    val firstName: NameField,
    val lastName: NameField,
    val email: EmailAddress,
    val userStatus: UserStatus,
) {
    companion object {
        fun from(id: String, appUserId: String, firstName: String, lastName: String, email: String, userStatus: UserStatus): AppUser =
            AppUser(
                id = UserId.from(id),
                appUserId = AppUserId.Companion(appUserId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email),
                userStatus = userStatus,
            )

        fun new(appUserId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                id = UserId.new(),
                appUserId = AppUserId.Companion(appUserId),
                firstName = NameField.Companion(firstName),
                lastName = NameField.Companion(lastName),
                email = EmailAddress.Companion(email),
                userStatus = UserStatus.ACTIVE
            )
    }
}

