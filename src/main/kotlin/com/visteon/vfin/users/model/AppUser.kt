package com.visteon.vfin.users.model

import com.visteon.vfin.types.NameField
import com.visteon.vfin.types.EmailAddress

data class AppUser(
    val id : UserId,
    val appUserId: AppUserId,
    val firstName: NameField,
    val lastName: NameField,
    val email: EmailAddress,
    val userStatus: UserStatus,
) {
    fun update(appUserId: String, firstName: String, lastName: String, email: String, userStatus: UserStatus): AppUser =
        this.copy(
            appUserId = AppUserId(appUserId),
            firstName = NameField(firstName),
            lastName = NameField(lastName),
            email = EmailAddress(email),
            userStatus = userStatus
        )


    companion object {
        fun from(id: String, appUserId: String, firstName: String, lastName: String, email: String, userStatus: UserStatus): AppUser =
            AppUser(
                id = UserId.from(id),
                appUserId = AppUserId(appUserId),
                firstName = NameField(firstName),
                lastName = NameField(lastName),
                email = EmailAddress(email),
                userStatus = userStatus,
            )

        fun new(appUserId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                id = UserId.new(),
                appUserId = AppUserId(appUserId),
                firstName = NameField(firstName),
                lastName = NameField(lastName),
                email = EmailAddress(email),
                userStatus = UserStatus.ACTIVE
            )
    }
}

