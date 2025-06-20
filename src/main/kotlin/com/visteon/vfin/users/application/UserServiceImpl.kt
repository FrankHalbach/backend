package com.visteon.vfin.users.application

import com.visteon.vfin.exception.DuplicateEntityException
import com.visteon.vfin.exception.EmailAlreadyTakenException
import com.visteon.vfin.exception.EntityNotFoundException
import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.UserId
import com.visteon.vfin.types.EmailAddress
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.visteon.vfin.users.application.AppUserCommandService
import com.visteon.vfin.users.AppUserQueryService



@Transactional
@Service
class AppUserService(private val repo: AppUserRepository) : AppUserCommandService, AppUserQueryService {

    override fun create(req: AppUserCreationRequest): AppUser {

        if(repo.appUserIdExists(AppUserId(req.appUserId))){
            throw DuplicateEntityException("User","Id", req.appUserId)
        }

        if(repo.emailAddressExists(req.email))
            throw EmailAlreadyTakenException(req.email)

        val newUser = AppUser.new(
            appUserId = AppUserId(req.appUserId),
            firstName = req.firstName,
            lastName = req.lastName,
            email = EmailAddress(req.email),
            userRoles = req.userRoles
        )

        repo.create(newUser)

        return newUser


    }

    override fun update(userId: UserId, req: AppUserUpdateRequest): AppUser {

        val existing = repo.getById(userId) ?: throw EntityNotFoundException("User", userId.value.toString())

        if(repo.appUserIdExistsOnOtherUser(userId,AppUserId(req.appUserId)))
            throw DuplicateEntityException("User","Id", req.appUserId)

        val updatedUser = existing.update(
            appUserId = AppUserId(req.appUserId),
            firstName = req.firstName,
            lastName = req.lastName,
            email = EmailAddress(req.email),
            userStatus = req.userStatus,
            userRoles = req.userRoles
        )

        repo.update(updatedUser)

        return updatedUser
    }

   override fun getById(userId: UserId): AppUser? = repo.getById(userId)

   override fun getByAppUserId(appUserId: String): AppUser? =
        repo.getByAppUserId(AppUserId(appUserId))

   override fun getAll(): List<AppUser> = repo.getAll()
}
