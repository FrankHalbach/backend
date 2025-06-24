package com.visteon.vfin.users.model

import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.EmailAddress

data class AppUser private constructor(
    val id : UserId,
    val appUserId: AppUserId,
    val firstName: String,
    val lastName: String,
    val email: EmailAddress,
    val userStatus: UserStatus,
    val userRoles: Set<UserRole>,
) {
    // Update user via copy.
    fun update(appUserId: AppUserId, firstName: String, lastName: String, email: EmailAddress, userStatus: UserStatus, userRoles: Set<UserRole>): AppUser =
     AppUser(
        id = this.id,
        appUserId = appUserId,
        firstName = firstName.trim(),
        lastName = lastName.trim(),
        email = email,
        userStatus = userStatus,
        userRoles = userRoles
    )      


    companion object {

    /* Factory method for reconstructing an existing AppUser from persistence.
       Should be used only by the repository.
     */
       fun load(
        id: UserId,
        appUserId: AppUserId,
        firstName: String,
        lastName: String,
        email: EmailAddress,
        userStatus: UserStatus,
        userRoles: Set<UserRole>
    ): AppUser = AppUser(
        id = id,
        appUserId = appUserId,
        firstName = firstName,
        lastName = lastName,
        email = email,
        userStatus = userStatus,
        userRoles = userRoles
    )

    // Create a new User.
    fun new(appUserId: AppUserId, firstName: String, lastName: String, email: EmailAddress, userRoles: Set<UserRole>): AppUser =
            AppUser(
                id = UserId.new(),
                appUserId = appUserId,
                firstName = firstName.trim(),
                lastName = lastName.trim(),
                email = email,
                userStatus = UserStatus.ACTIVE,
                userRoles = userRoles
            )
    }
}

