package com.visteon.vfin.controller

import com.visteon.vfin.users.infrastructure.AppUserRepository
import com.visteon.vfin.users.model.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class AppUserController(
    private val repo: AppUserRepository,
    ) {

    @PostMapping
    fun createUser(@Valid @RequestBody req: CreateAppUserRequest): ResponseEntity<UserResponse> {
        val user = repo.create(req.toDomain())
        return  ResponseEntity.ok(user.toResponse())
    }

    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable userId: String,
        @Valid @RequestBody req: UpdateAppUserRequest
    ): ResponseEntity<UserResponse> {
        val updated = repo.update( req.toDomain(userId))
        return ResponseEntity.ok(updated.toResponse())
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<UserResponse> =
        repo.getById(AppUserId(userId))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val result = repo.getAll().map { it.toResponse() }
        return ResponseEntity.ok(result)
    }

}