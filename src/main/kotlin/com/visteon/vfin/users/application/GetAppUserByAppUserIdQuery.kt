package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.users.domain.AppUserId
import com.visteon.vfin.users.domain.AppUser

data class GetAppUserByUserId(val userId:AppUserId)

@Query
class GetUserByUserIdQueryHandler(
    private val userRepository: AppUserRepository
) {
    fun handle(query: GetAppUserByUserId): AppUser? {
        return userRepository.getById(query.userId)
    }
}