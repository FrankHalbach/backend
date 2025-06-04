package com.visteon.vfin.users.application

import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateAppUserRequest(

    @field:NotBlank
    @field:Pattern(regexp = AppUserId.Companion.REGEX_PATTERN, message = AppUserId.Companion.VALIDATION_MESSAGE)
    val appUserId: String,

    @field:NotBlank
    @field:Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val firstName: String,

    @field:NotBlank
    @field:Size(min = NameField.Companion.MIN_LENGTH,max = NameField.Companion.MAX_LENGTH)
    val lastName: String,

    @field:NotBlank
    @field:Email
    val email: String,

    // need validation
    val userStatus: UserStatus
)