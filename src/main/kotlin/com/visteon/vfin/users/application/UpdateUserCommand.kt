package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.users.domain.User

data class UpdateUserCommand(
    val firstName: String,
    val lastName: String,
    val email: String
)

@Command
class UpdateUserCommandHandler(
    private val userRepository: UserRepository
) {
    fun handle(userId: String, command: UpdateUserCommand): User {
        val updatedUser = User.from(
            userId,
            command.firstName,
            command.lastName,
            command.email
        )
        return userRepository.update(updatedUser)
    }
}