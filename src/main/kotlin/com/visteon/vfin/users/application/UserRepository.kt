package com.visteon.vfin.users.application

import com.visteon.vfin.users.domain.User
import com.visteon.vfin.users.domain.UserId

interface UserRepository {
    fun create(user: User): User
    fun getById(userId: UserId): User?
    fun update(updated: User): User
    fun getAll(): List<User>
}