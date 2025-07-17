package com.visteon.vfin.users.model

import com.visteon.vfin.sharedkernel.identifiers.UserId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.visteon.vfin.sharedkernel.types.EmailAddress

internal class UserTest {

    @Test
    fun `should create AppUser from valid value types`() {
        val id = UserId.new()
        val appUserId = AppUserId(" ID1 ")
        val firstName = FirstName(" John ")
        val lastName = LastName(" Doe ")
        val email = EmailAddress(" john.doe@domain.com ")
        val roles = setOf(UserRole.ADMIN)

        val user = AppUser(id, appUserId, firstName, lastName, email, UserStatus.ACTIVE, roles)

        assertEquals(id, user.id)
        assertEquals(appUserId, user.appUserId)
        assertEquals(firstName, user.firstName)
        assertEquals(lastName, user.lastName)
        assertEquals(email, user.email)
        assertEquals(UserStatus.ACTIVE, user.userStatus)
        assertEquals(roles, user.userRoles)
    }


}