package com.visteon.vfin.user

import com.visteon.vfin.user.model.AppUser
import com.visteon.vfin.sharedkernel.identifiers.UserId


interface AppUserQueryService {

    fun getById(userId: UserId): AppUser?

    fun getByAppUserId(appUserId: String): AppUser?

    fun getAll(): List<AppUser>
}
