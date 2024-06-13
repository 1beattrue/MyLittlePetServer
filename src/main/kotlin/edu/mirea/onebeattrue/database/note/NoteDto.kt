package edu.mirea.onebeattrue.database.note

import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val id: Int = UNDEFINED_ID,
    val text: String,
    val iconResId: Int,
    val petId: Int,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}