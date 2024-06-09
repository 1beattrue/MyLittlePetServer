package edu.mirea.onebeattrue.database.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val token: String
)