package com.visteon.vfin.users

import com.visteon.vfin.users.application.AppUserCreationRequest
import com.visteon.vfin.users.application.AppUserService
import com.visteon.vfin.users.application.AppUserUpdateRequest
import com.visteon.vfin.users.application.UserResponse
import com.visteon.vfin.users.application.toResponse
import com.visteon.vfin.users.model.UserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class AppUserController(
    private val service: AppUserService,
) {

    @PostMapping
    fun createUser(@Valid @RequestBody req: AppUserCreationRequest): ResponseEntity<UserResponse> {

        val appUser = service.create(req)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(appUser.id.value.toString())
            .toUri()

        return  ResponseEntity.created(location).body(appUser.toResponse())
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @Valid @RequestBody req: AppUserUpdateRequest
    ): ResponseEntity<UserResponse> =
        ResponseEntity.ok(service.update(UserId(id),req).toResponse())

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<UserResponse> =
        service.getById(UserId(id))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/by-user-id/{appUserId}")
    fun getByAppUserId(@PathVariable appUserId: String): ResponseEntity<UserResponse> =
        service.getByAppUserId(appUserId)
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> =
        ResponseEntity.ok(  service.getAll().map { it.toResponse() })



}