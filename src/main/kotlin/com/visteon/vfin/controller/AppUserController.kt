package com.visteon.vfin.controller

import com.visteon.vfin.users.application.AppUserService
import com.visteon.vfin.users.application.CreateAppUserRequest
import com.visteon.vfin.users.application.UpdateAppUserRequest
import com.visteon.vfin.users.application.UserResponse
import com.visteon.vfin.users.model.UserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class AppUserController(
    private val service: AppUserService,
) {

    @PostMapping
    fun createUser(@Valid @RequestBody req: CreateAppUserRequest): ResponseEntity<UserId> {
        val user = service.create(req)
        return  ResponseEntity.ok(user)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: String,
        @Valid @RequestBody req: UpdateAppUserRequest
    ): ResponseEntity<Unit> {
        val updated = service.update(id,req)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<UserResponse> =
        service.getById(id)
            ?. let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/by-user-id/{appUserId}")
    fun getByAppUserId(@PathVariable appUserId: String): ResponseEntity<UserResponse> =
        service.getByAppUserId(appUserId)
            ?. let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val result = service.getAll()
        return ResponseEntity.ok(result)
    }

}