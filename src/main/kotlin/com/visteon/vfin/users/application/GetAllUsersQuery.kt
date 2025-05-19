package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Query
import com.visteon.vfin.users.ui.UserResponse
import com.visteon.vfin.users.ui.toResponse


@Query
class GetAllUsersQueryHandler(
    private val userRepository: UserRepository
) {
    fun handle(): List<UserResponse> {
        return userRepository.getAll()
            .map { it.toResponse() }
    }
}