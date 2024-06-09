package edu.mirea.onebeattrue.database.pet

import kotlinx.serialization.Serializable

@Serializable
data class PetDto(
    val id: Int,
    val type: String,
    val name: String,
    val imageUrl: String?,
    val dateOfBirth: Long?,
    val weight: Float?
)