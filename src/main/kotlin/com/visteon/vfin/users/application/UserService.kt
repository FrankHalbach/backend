package com.visteon.vfin.users.application

import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.AppUserId
import com.visteon.vfin.users.model.UserId
import org.springframework.stereotype.Service

@Service
class AppUserService(private val repo: AppUserRepository) {

    fun create(req: CreateAppUserRequest): UserResponse =
        repo.create(req.toDomain()).toResponse()

    fun update(id: String, req: UpdateAppUserRequest): UserResponse =
        repo.update(req.toDomain(id)).toResponse()

    fun getById(id: String): UserResponse? =
        repo.getById(UserId.from(id))?.toResponse()

    fun getByAppUserId(appUserId: String): UserResponse? =
        repo.getByAppUserId(AppUserId(appUserId))?.toResponse()

    fun getAll(): List<UserResponse> =
        repo.getAll().map { it.toResponse() }
}