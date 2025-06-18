package com.visteon.vfin.users.application

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AppUserCreationRequest(

    @field:NotBlank
    @field:Pattern(regexp = AppUserId.Companion.REGEX_PATTERN, message = AppUserId.Companion.VALIDATION_MESSAGE)
    val appUserId: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val firstName: String,

    @field:NotBlank
    @field:Size(max = FieldLengths.LABEL_MAX)
    val lastName: String,

    @field:NotBlank
    @field:Email
    val email: String,

    // need validation

    val userStatus: UserStatus
)