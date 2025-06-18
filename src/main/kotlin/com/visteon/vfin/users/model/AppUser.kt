package com.visteon.vfin.users.model

import com.visteon.vfin.users.UserId
import com.visteon.vfin.types.EmailAddress

data class AppUser(
    val id : UserId,
    val appUserId: AppUserId,
    val firstName: String,
    val lastName: String,
    val email: EmailAddress,
    val userStatus: UserStatus,
) {
    fun update(appUserId: String, firstName: String, lastName: String, email: String, userStatus: UserStatus): AppUser =
        this.copy(
            appUserId = AppUserId(appUserId),
            firstName = firstName,
            lastName = lastName,
            email = EmailAddress(email),
            userStatus = userStatus
        )


    companion object {

        fun new(appUserId: String, firstName: String, lastName: String, email: String): AppUser =
            AppUser(
                id = UserId.new(),
                appUserId = AppUserId(appUserId),
                firstName = firstName,
                lastName = lastName,
                email = EmailAddress(email),
                userStatus = UserStatus.ACTIVE
            )
    }
}

