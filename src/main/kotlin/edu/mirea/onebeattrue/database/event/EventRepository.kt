package edu.mirea.onebeattrue.database.event

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.less
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object EventRepository {

    fun createEvent(event: EventDto): Int {
        val insertStatement = transaction {
            Events.insert {
                it[time] = event.time
                it[label] = event.label
                it[repeatable] = event.repeatable
                it[petId] = event.petId
            }
        }

        return insertStatement.resultedValues?.first()?.get(Events.id)
            ?: throw IllegalStateException("Failed to insert Event")
    }

    fun getEventById(id: Int): EventDto? {
        return transaction {
            Events.selectAll().where { Events.id eq id }
                .map {
                    EventDto(
                        id = it[Events.id],
                        time = it[Events.time],
                        label = it[Events.label],
                        repeatable = it[Events.repeatable],
                        petId = it[Events.petId]
                    )
                }
                .singleOrNull()
        }
    }

    fun updateEvent(event: EventDto): Boolean {
        return transaction {
            Events.update({ Events.id eq event.id }) {
                it[time] = event.time
                it[label] = event.label
                it[repeatable] = event.repeatable
                it[petId] = event.petId
            } > 0
        }
    }

    fun deleteEvent(id: Int): Boolean {
        return transaction {
            Events.deleteWhere { Events.id eq id } > 0
        }
    }

    fun getEventsByPetId(petId: Int): List<EventDto> {
        return transaction {
            Events.selectAll().where { Events.petId eq petId }
                .map {
                    EventDto(
                        id = it[Events.id],
                        time = it[Events.time],
                        label = it[Events.label],
                        repeatable = it[Events.repeatable],
                        petId = it[Events.petId]
                    )
                }
        }
    }

    fun deleteIrrelevantEvents(petId: Int): Int {
        val currentTime = Instant.now().toEpochMilli()

        return transaction {
            Events.deleteWhere {
                (time less currentTime) and
                        (repeatable eq false) and
                        (Events.petId eq petId)
            }
        }
    }
}