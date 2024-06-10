package edu.mirea.onebeattrue.database.note

data class NoteDto(
    val id: Int,
    val text: String,
    val iconResId: Int,
    val petId: Int,
)