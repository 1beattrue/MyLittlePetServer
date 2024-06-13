package edu.mirea.onebeattrue.database.event

import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    val id: Int = UNDEFINED_ID,
    val time: Long,
    val label: String,
    val repeatable: Boolean,
    val petId: Int,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
