package com.visteon.vfin.controller

import com.visteon.vfin.users.application.AppUserService
import com.visteon.vfin.users.application.AppUserCreationRequest
import com.visteon.vfin.users.application.AppUserUpdateRequest
import com.visteon.vfin.users.application.AppUserCreationResponse
import com.visteon.vfin.users.application.UserResponse
import com.visteon.vfin.users.model.UserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class AppUserController(
    private val service: AppUserService,
) {

    @PostMapping
    fun createUser(@Valid @RequestBody req: AppUserCreationRequest): ResponseEntity<AppUserCreationResponse> {
        val userId = service.create(req)
        return  ResponseEntity.ok(AppUserCreationResponse(userId.value.toString()))
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @Valid @RequestBody req: AppUserUpdateRequest
    ): ResponseEntity<Unit> {
        service.update(UserId(id),req)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<UserResponse> =
        service.getById(UserId(id))
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