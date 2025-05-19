package com.visteon.vfin.users.infrastructure

import com.visteon.vfin.users.application.UserRepository
import com.visteon.vfin.users.domain.User
import com.visteon.vfin.users.domain.UserId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface UserEntityRepository : CrudRepository<UserEntity, Long> {
   fun findByUserId(userId: String): UserEntity?
   fun findByEmail(email: String): UserEntity?
}

@Repository
class UserRepositoryImpl(
   private val crudRepo: UserEntityRepository
) : UserRepository {

   override fun create(user: User): User {
      return crudRepo.save(user.toEntity(null)).toDomain()
   }

   override fun getById(userId: UserId): User? {
      return crudRepo.findByUserId(userId.value)?.toDomain()
   }

   override fun update(updated: User): User {
      val dbUsr = crudRepo.findByUserId(updated.userId.value)
         ?: throw NoSuchElementException("User with ID ${updated.userId.value} not found")

      val entity = updated.toEntity(dbUsr.id)

      return crudRepo.save(entity).toDomain()
   }

   override fun getAll(): List<User> {
      return crudRepo.findAll().map { it.toDomain() }
   }

}