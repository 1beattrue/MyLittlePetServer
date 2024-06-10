package edu.mirea.onebeattrue.database.event

data class EventDto(
    val id: Int,
    val time: Long,
    val label: String,
    val repeatable: Boolean,
    val petId: Int,
)
