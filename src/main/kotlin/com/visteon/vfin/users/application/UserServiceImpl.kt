package com.visteon.vfin.users.application

import com.visteon.vfin.exception.DuplicateEntityException
import com.visteon.vfin.exception.EmailAlreadyTakenException
import com.visteon.vfin.exception.EntityNotFoundException
import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.sharedkernel.types.EmailAddress
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.visteon.vfin.users.AppUserQueryService
import com.visteon.vfin.users.model.FirstName
import com.visteon.vfin.users.model.LastName
import com.visteon.vfin.users.model.UserStatus


@Transactional
@Service
class AppUserService(private val repo: AppUserRepository) : AppUserCommandService, AppUserQueryService {

    override fun create(req: AppUserCreationRequest): AppUser {

        if(repo.appUserIdExists(AppUserId(req.appUserId))){
            throw DuplicateEntityException("User","Id", req.appUserId)
        }

        if(repo.emailAddressExists(req.email))
            throw EmailAlreadyTakenException(req.email)

        val newUser = AppUser(
            id = UserId.new(),
            appUserId = AppUserId(req.appUserId),
            firstName = FirstName(req.firstName),
            lastName = LastName(req.lastName),
            email = EmailAddress(req.email),
            userStatus = UserStatus.ACTIVE,
            userRoles = req.userRoles
        )

        repo.create(newUser)

        return newUser


    }

    override fun update(userId: UserId, req: AppUserUpdateRequest): AppUser {

        val existing = repo.getById(userId) ?: throw EntityNotFoundException("User", userId.value.toString())

        if(repo.appUserIdExistsOnOtherUser(userId,AppUserId(req.appUserId)))
            throw DuplicateEntityException("User","Id", req.appUserId)

        val updatedUser = existing.copy(
            appUserId = AppUserId(req.appUserId),
            firstName = FirstName(req.firstName),
            lastName = LastName(req.lastName),
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
