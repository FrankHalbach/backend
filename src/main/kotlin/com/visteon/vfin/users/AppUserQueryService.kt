package com.visteon.vfin.users

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.sharedkernel.identifiers.UserId


interface AppUserQueryService {

    fun getById(userId: UserId): AppUser?

    fun getByAppUserId(appUserId: String): AppUser?

    fun getAll(): List<AppUser>
}
