package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.application.AppUserRepository
import com.visteon.vfin.users.domain.AppUser
import com.visteon.vfin.users.domain.AppUserId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface UserEntityRepository : CrudRepository<UserEntity, Int> {
   fun findByUserId(userId: String): UserEntity?
   //fun findByEmail(email: String): UserEntity?
}

@Repository
class UserRepositoryImpl(
   private val crudRepo: UserEntityRepository
) : AppUserRepository {

   override fun create(user: AppUser): AppUser {
      return crudRepo.save(user.toEntity(null)).toDomain()
   }

   override fun getById(userId: AppUserId): AppUser? {
      return crudRepo.findByUserId(userId.value)?.toDomain()
   }

   override fun update(updated: AppUser): AppUser {
      val dbUsr = crudRepo.findByUserId(updated.userId.value)
         ?: throw NoSuchElementException("User with ID ${updated.userId.value} not found")

      val entity = updated.toEntity(dbUsr.id)

      return crudRepo.save(entity).toDomain()
   }

   override fun getAll(): List<AppUser> {
      return crudRepo.findAll().map { it.toDomain() }
   }

}