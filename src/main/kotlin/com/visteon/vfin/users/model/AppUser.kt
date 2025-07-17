package com.visteon.vfin.users.model

import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.EmailAddress

data class AppUser(
    val id : UserId,
    val appUserId: AppUserId,
    val firstName: FirstName,
    val lastName: LastName,
    val email: EmailAddress,
    val userStatus: UserStatus,
    val userRoles: Set<UserRole>,
)

