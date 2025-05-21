package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.users.domain.UserId
import com.visteon.vfin.users.domain.User

@Query
class GetUserByUserIdQueryHandler(
    private val userRepository: UserRepository
) {
    fun handle(userId: String): User? {
        return userRepository.getById(UserId(userId))
    }
}