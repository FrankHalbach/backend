package com.visteon.vfin.user.application

import com.visteon.vfin.common.FieldLengths
import com.visteon.vfin.user.model.AppUserId
import com.visteon.vfin.user.model.UserStatus
import com.visteon.vfin.user.model.UserRole
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.NotEmpty

data class AppUserUpdateRequest(

    @field:NotBlank
    @field:Pattern(regexp = AppUserId.REGEX_PATTERN, message = AppUserId.VALIDATION_MESSAGE)
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

    val userStatus: UserStatus,

    @field:NotEmpty(message = "At least one role must be assigned")
    val userRoles: Set<UserRole>

)