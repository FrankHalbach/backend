package com.visteon.vfin.users.ui

import com.visteon.vfin.users.application.CreateUserCommand
import com.visteon.vfin.users.application.CreateUserCommandHandler
import com.visteon.vfin.users.application.GetAllUsersQueryHandler
import com.visteon.vfin.users.application.GetAppUserByUserId
import com.visteon.vfin.users.application.GetUserByUserIdQueryHandler
import com.visteon.vfin.users.application.UpdateUserCommand
import com.visteon.vfin.users.application.UpdateUserCommandHandler
import com.visteon.vfin.users.domain.AppUserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class AppUserController(
    private val createUser: CreateUserCommandHandler,
    private val updateUser: UpdateUserCommandHandler,
    private val getUserByUserId: GetUserByUserIdQueryHandler,
    private val getAllUsers: GetAllUsersQueryHandler,
    ) {

    @PostMapping
    fun createUser(@Valid @RequestBody command: CreateUserCommand): ResponseEntity<UserResponse> {
        val user = createUser.handle(command)
        return  ResponseEntity.ok(user.toResponse())
    }

    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable userId: String,
        @Valid @RequestBody body: UpdateUserCommand
    ): ResponseEntity<UserResponse> {
        val updated = updateUser.handle(userId, body)
        return ResponseEntity.ok(updated.toResponse())
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<UserResponse> =
        getUserByUserId.handle(GetAppUserByUserId(AppUserId(userId)))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val result = getAllUsers.handle().map { it.toResponse() }
        return ResponseEntity.ok(result)
    }

}
