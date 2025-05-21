package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.users.domain.User

@Query
class GetAllUsersQueryHandler(
    private val userRepository: UserRepository
) {
    fun handle(): List<User> {
        return userRepository.getAll()
    }
}