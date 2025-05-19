package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.users.domain.User

data class CreateUserCommand(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

@Command
class CreateUserCommandHandler(
    private val userRepository: UserRepository
) {
    fun handle(command: CreateUserCommand): User {
        val user = User.from(command.userId, command.firstName, command.lastName, command.email)
        return userRepository.create(user)
    }
}