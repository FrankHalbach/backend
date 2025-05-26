package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.domain.AppUser
import com.visteon.vfin.users.domain.AppUserId
import jakarta.validation.constraints.*

data class CreateUserCommand(

    @field:NotBlank
    @field:Pattern(regexp = AppUserId.REGEX_PATTERN, message = AppUserId.VALIDATION_MESSAGE)
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
    private val userRepository: AppUserRepository
) {
    fun handle(command: CreateUserCommand): AppUser {
        val user = AppUser.from(command.userId, command.firstName, command.lastName, command.email)
        return userRepository.create(user)
    }
}