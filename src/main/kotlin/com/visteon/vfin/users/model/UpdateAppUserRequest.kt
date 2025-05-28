package com.visteon.vfin.users.model

import com.visteon.vfin.common.types.NameField
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateAppUserRequest(
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