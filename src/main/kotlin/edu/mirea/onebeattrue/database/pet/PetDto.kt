package edu.mirea.onebeattrue.database.pet

import kotlinx.serialization.Serializable

@Serializable
data class PetDto(
    val id: Int = UNDEFINED_ID,
    val type: String,
    val name: String,
    val imageUrl: String? = null,
    val dateOfBirth: Long? = null,
    val weight: Float? = null,
    val userToken: String
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}