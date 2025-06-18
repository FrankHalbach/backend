package com.visteon.vfin.users.application

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.UserId


interface AppUserCommandService {

    fun create(req: AppUserCreationRequest): AppUser

    fun update(userId: UserId, req: AppUserUpdateRequest): AppUser

}
