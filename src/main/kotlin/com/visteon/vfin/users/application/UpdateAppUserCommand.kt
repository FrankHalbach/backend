package com.visteon.vfin.users.application

import com.visteon.vfin.common.annotation.Command
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.domain.AppUser
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateUserCommand(
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
class UpdateUserCommandHandler(
    private val userRepository: AppUserRepository
) {
    fun handle(userId: String, command: UpdateUserCommand): AppUser {
        val updatedUser = AppUser.from(
            userId,
            command.firstName,
            command.lastName,
            command.email
        )
        return userRepository.update(updatedUser)
    }
}