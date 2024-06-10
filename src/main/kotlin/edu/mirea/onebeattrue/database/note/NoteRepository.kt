package edu.mirea.onebeattrue.database.note

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object NoteRepository {

    fun createNote(note: NoteDto): Int {
        val insertStatement = transaction {
            Notes.insert {
                it[text] = note.text
                it[iconResId] = note.iconResId
                it[petId] = note.petId
            }
        }

        return insertStatement.resultedValues?.first()?.get(Notes.id)
            ?: throw IllegalStateException("Failed to insert Note")
    }

    fun getNoteById(id: Int): NoteDto? {
        return transaction {
            Notes.selectAll().where { Notes.id eq id }
                .map {
                    NoteDto(
                        id = it[Notes.id],
                        text = it[Notes.text],
                        iconResId = it[Notes.iconResId],
                        petId = it[Notes.petId]
                    )
                }
                .singleOrNull()
        }
    }

    fun updateNote(note: NoteDto): Boolean {
        return transaction {
            Notes.update({ Notes.id eq note.id }) {
                it[text] = note.text
                it[iconResId] = note.iconResId
                it[petId] = note.petId
            } > 0
        }
    }

    fun deleteNote(id: Int): Boolean {
        return transaction {
            Notes.deleteWhere { Notes.id eq id } > 0
        }
    }

    fun getNotesByPetId(petId: Int): List<NoteDto> {
        return transaction {
            Notes.selectAll().where { Notes.petId eq petId }
                .map {
                    NoteDto(
                        id = it[Notes.id],
                        text = it[Notes.text],
                        iconResId = it[Notes.iconResId],
                        petId = it[Notes.petId]
                    )
                }
        }
    }
}