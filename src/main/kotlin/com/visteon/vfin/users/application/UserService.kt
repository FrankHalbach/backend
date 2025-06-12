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

    fun create(req: AppUserCreationRequest): AppUser {

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

        repo.create(newUser)

        return newUser


    }

    fun update(userId: UserId, req: AppUserUpdateRequest): AppUser {

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

        repo.update(updatedUser)

        return updatedUser
    }

    fun getById(userId: UserId): AppUser? = repo.getById(userId)

    fun getByAppUserId(appUserId: String): AppUser? =
        repo.getByAppUserId(AppUserId(appUserId))

    fun getAll(): List<AppUser> = repo.getAll()
}
