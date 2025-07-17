package com.visteon.vfin.users.api
import com.visteon.vfin.users.*
import com.visteon.vfin.users.application.*
import com.visteon.vfin.sharedkernel.identifiers.UserId
import com.visteon.vfin.plant.application.PlantService
import com.visteon.vfin.users.model.UserStatus
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/api/admin/users")
class AppUserController(
    private val cmdService: AppUserCommandService,
    private val queryService: AppUserQueryService,
    
) {

    @PostMapping
    fun createUser(@Valid @RequestBody req: AppUserCreationRequest): ResponseEntity<UserResponse> {

        val appUser = cmdService.create(req)

        // do we even need this?
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
        ResponseEntity.ok(cmdService.update(UserId(id),req).toResponse())

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<UserResponse> =
        queryService.getById(UserId(id))
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/by-user-id/{appUserId}")
    fun getByAppUserId(@PathVariable appUserId: String): ResponseEntity<UserResponse> =
        queryService.getByAppUserId(appUserId)
            ?. let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> =
        ResponseEntity.ok(  queryService.getAll().map { it.toResponse() })



}