package com.visteon.vfin.users.application

import com.visteon.vfin.users.domain.AppUser
import com.visteon.vfin.users.domain.AppUserId

interface AppUserRepository {
    fun create(user: AppUser): AppUser
    fun update(updated: AppUser): AppUser
    fun getById(userId: AppUserId): AppUser?
    fun getAll(): List<AppUser>
}