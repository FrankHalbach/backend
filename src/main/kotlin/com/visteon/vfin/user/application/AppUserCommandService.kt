package com.visteon.vfin.user.application

import com.visteon.vfin.user.model.AppUser
import com.visteon.vfin.sharedkernel.identifiers.UserId


interface AppUserCommandService {

    fun create(req: AppUserCreationRequest): AppUser

    fun update(userId: UserId, req: AppUserUpdateRequest): AppUser

}
