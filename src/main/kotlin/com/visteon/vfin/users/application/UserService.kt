package com.visteon.vfin.users.application

import com.visteon.vfin.common.exception.DuplicateEntityException
import com.visteon.vfin.common.exception.EmailAlreadyTakenException
import com.visteon.vfin.common.exception.EntityNotFoundException
import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AppUserService(private val repo: AppUserRepository) {

    fun create(req: AppUserCreationRequest): UserId {

        if(repo.appUserIdExists(AppUserId(req.appUserId))){
            throw DuplicateEntityException("User","Id", req.appUserId)
        }

        if(repo.emailAddressExists(req.email))
            throw EmailAlreadyTakenException(req.email)

        val newUser = AppUser.new(
            appUserId = req.appUserId,
            firstName = req.firstName,
            lastName = req.lastName,
            email = req.email
        )

        return repo.create(newUser)
    }

    fun update(userId: UserId, req: AppUserUpdateRequest) {

        val existing = repo.getById(userId) ?: throw EntityNotFoundException("User", userId.value.toString())

        if(repo.appUserIdExistsOnOtherUser(userId,AppUserId(req.appUserId)))
            throw DuplicateEntityException("User","Id", req.appUserId)

        val updatedUser = existing.update(
            appUserId = req.appUserId,
            firstName = req.firstName,
            lastName = req.lastName,
            email = req.email,
            userStatus = req.userStatus
        )

        return repo.update(updatedUser)
    }

    fun getById(userId: UserId): UserResponse? =
        repo.getById(userId)?.toResponse()

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