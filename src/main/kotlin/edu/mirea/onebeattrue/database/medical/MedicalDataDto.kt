package edu.mirea.onebeattrue.database.medical

import kotlinx.serialization.Serializable

@Serializable
data class MedicalDataDto(
    val id: Int = UNDEFINED_ID,
    val type: String,
    val imageUrl: String? = null,
    val text: String,
    val petId: Int
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}