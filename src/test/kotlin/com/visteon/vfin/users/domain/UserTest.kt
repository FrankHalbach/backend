package com.visteon.vfin.users.domain

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test
import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.NameField

internal class UserTest {

    @Test
    fun `should create User from valid inputs`() {
        val user = User.from(" ID1 ", " John ", " Doe ", " a@b.com ")
        assertEquals("id1", user.userId.value)
        assertEquals("John", user.firstName.value)
        assertEquals("Doe", user.lastName.value)
        assertEquals("a@b.com", user.email.value)
    }

    @Test
    fun `should throw exception for invalid email`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            User.from("id", "John", "Doe", "bad-email")
        }
        assertEquals(
            "Email must match pattern: ${EmailAddress.REGEX.pattern}",
            exception.message
        )
    }
}