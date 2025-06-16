package com.visteon.vfin.users.model

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test
import com.visteon.vfin.types.EmailAddress

internal class UserTest {

    @Test
    fun `should create User from valid inputs`() {


        val user = AppUser.new(" ID1", " John ", " Doe ", " a@b.com ")
        assertEquals("id1", user.appUserId.value)
        assertEquals("John", user.firstName.value)
        assertEquals("Doe", user.lastName.value)
        assertEquals("a@b.com", user.email.value)
    }

    @Test
    fun `should throw exception for invalid email`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            AppUser.new("id", "John", "Doe", "bad-email")
        }
        assertEquals(
            "Email must match pattern: ${EmailAddress.REGEX.pattern}",
            exception.message
        )
    }
}