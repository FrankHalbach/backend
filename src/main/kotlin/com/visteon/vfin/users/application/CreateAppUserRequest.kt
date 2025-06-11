package com.visteon.vfin.users.application

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateAppUserRequest(

    @NotBlank
    @Pattern(regexp = AppUserId.Companion.REGEX_PATTERN, message = AppUserId.Companion.VALIDATION_MESSAGE)
    val appUserId: String,

    @NotBlank
    @Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val firstName: String,

    @NotBlank
    @Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val lastName: String,

    @NotBlank
    @Email
    val email: String,

    // need validation
    val userStatus: UserStatus
)