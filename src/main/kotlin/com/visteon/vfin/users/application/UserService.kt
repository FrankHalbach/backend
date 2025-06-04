package com.visteon.vfin.users.application

import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.springframework.stereotype.Service

@Service
class AppUserService(private val repo: AppUserRepository) {

    fun create(req: CreateAppUserRequest): UserResponse {

        val newUser = AppUser.new(
            appUserId = req.appUserId,
            firstName = req.firstName,
            lastName = req.lastName,
            email = req.email
        )

        return repo.save(newUser).toResponse()
    }

    fun update(id: String, req: UpdateAppUserRequest): UserResponse {

        val updateRequest = AppUser.from(
            id = id,
            appUserId = req.appUserId,
            firstName = req.firstName,
            lastName = req.lastName,
            email = req.email,
            userStatus = req.userStatus
        )

        val existing = repo.getById(UserId.from(id)) ?: throw NoSuchElementException("User with ID ${req.appUserId} not found\"")

        val updated = existing.updatedFrom(updateRequest)

        return repo.save(updated).toResponse()
    }

    fun getById(id: String): UserResponse? =
        repo.getById(UserId.from(id))?.toResponse()

    fun getByAppUserId(appUserId: String): UserResponse? =
        repo.getByAppUserId(AppUserId(appUserId))?.toResponse()

    fun getAll(): List<UserResponse> =
        repo.getAll().map { it.toResponse() }
}

fun AppUser.toResponse(): UserResponse = UserResponse(
    id = this.id.value.toString(),
    appUserId = this.appUserId.value,
    firstName = this.firstName.value,
    lastName = this.lastName.value,
    email = this.email.value,
    userStatus = this.userStatus
)