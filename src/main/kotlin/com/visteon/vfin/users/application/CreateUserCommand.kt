package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.domain.User
import com.visteon.vfin.users.domain.UserId
import jakarta.validation.constraints.*

data class CreateUserCommand(

    @field:NotBlank
    @field:Pattern(regexp = UserId.REGEX_PATTERN, message = UserId.VALIDATION_MESSAGE)
    val userId: String,

    @field:NotBlank
    @field:Size(min = NameField.MIN_LENGTH,max = NameField.MAX_LENGTH)
    val firstName: String,

    @field:NotBlank
    @field:Size(min = NameField.MIN_LENGTH,max = NameField.MAX_LENGTH)
    val lastName: String,

    @field:NotBlank
    @field:Email
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