package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.users.domain.AppUser

@Query
class GetAllUsersQueryHandler(
    private val userRepository: AppUserRepository
) {
    fun handle(): List<AppUser> {
        return userRepository.getAll()
    }
}