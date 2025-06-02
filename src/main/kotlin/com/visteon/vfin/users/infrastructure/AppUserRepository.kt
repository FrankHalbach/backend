package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.model.AppUser
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface UserEntityRepository : CrudRepository<UserEntity, UUID> {
   fun findByUserId(userId: String): UserEntity?
   //fun findByEmail(email: String): UserEntity?
}

@Repository
class AppUserRepository(
   private val crudRepo: UserEntityRepository
)  {

    fun create(user: AppUser): AppUser {
      return crudRepo.save(user.toEntity()).toDomain()
   }

    fun getById(userId: UserId): AppUser? {
        return crudRepo.findById(userId.value).orElse(null)?.toDomain()
    }


    fun getByAppUserId(userId: AppUserId): AppUser? {
        return crudRepo.findByUserId(userId.value)?.toDomain()
    }

    fun update(updated: AppUser): AppUser {
      val dbUsr = crudRepo.findByUserId(updated.userId.value)
         ?: throw NoSuchElementException("User with ID ${updated.userId.value} not found")

      val entity = updated.toEntity()

      return crudRepo.save(entity).toDomain()
   }

    fun getAll(): List<AppUser> {
      return crudRepo.findAll().map { it.toDomain() }
   }

}