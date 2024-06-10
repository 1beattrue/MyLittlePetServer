package edu.mirea.onebeattrue.database.medical

data class MedicalDataDto(
    val id: Int,
    val type: String,
    val imageUrl: String?,
    val text: String,
    val petId: Int
)