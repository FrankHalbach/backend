package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface UserEntityRepository : CrudRepository<UserEntity, UUID> {
   fun findByAppUserId(userId: String): UserEntity?
   //fun findByEmail(email: String): UserEntity?
}

@Repository
class AppUserRepository(
   private val crudRepo: UserEntityRepository
)  {

    fun save(user: AppUser): AppUser {

        val entity =  UserEntity(
            id = user.id.value,
            appUserId = user.appUserId.value,
            firstName = user.firstName.value,
            lastName = user.lastName.value,
            email = user.email.value,
            userStatus = user.userStatus
        )

      return crudRepo.save(entity).toDomain()
   }

    fun getById(userId: UserId): AppUser? {
        return crudRepo.findById(userId.value).orElse(null)?.toDomain()
    }

    fun getByAppUserId(userId: AppUserId): AppUser? {
        return crudRepo.findByAppUserId(userId.value)?.toDomain()
    }

    fun getAll(): List<AppUser> {
      return crudRepo.findAll().map { it.toDomain() }
   }

}

// Domain ↔ Entity
fun UserEntity.toDomain(): AppUser =
    AppUser(
        id = UserId(this.id),
        appUserId = AppUserId(this.appUserId),
        firstName = NameField(this.firstName),
        lastName = NameField(this.lastName),
        email = EmailAddress(this.email),
        userStatus = this.userStatus
    )