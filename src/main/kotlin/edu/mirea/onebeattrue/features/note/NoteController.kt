package edu.mirea.onebeattrue.features.note

import edu.mirea.onebeattrue.database.note.NoteDto
import edu.mirea.onebeattrue.database.note.NoteRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class NoteController {

    suspend fun createNote(call: ApplicationCall) {
        val noteDto = call.receive<NoteDto>()
        val id = NoteRepository.createNote(noteDto)
        call.respond(HttpStatusCode.Created, mapOf("id" to id))
    }

    suspend fun getNoteById(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val note = NoteRepository.getNoteById(id)
        if (note != null) {
            call.respond(HttpStatusCode.OK, note)
        } else {
            call.respond(HttpStatusCode.NotFound, "Note not found")
        }
    }

    suspend fun updateNote(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")
        val noteDto = call.receive<NoteDto>().copy(id = id)

        val updated = NoteRepository.updateNote(noteDto)
        if (updated) {
            call.respond(HttpStatusCode.OK, "Note updated")
        } else {
            call.respond(HttpStatusCode.NotFound, "Note not found")
        }
    }

    suspend fun deleteNote(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val deleted = NoteRepository.deleteNote(id)
        if (deleted) {
            call.respond(HttpStatusCode.OK, "Note deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "Note not found")
        }
    }

    suspend fun getNotesByPetId(call: ApplicationCall) {
        val petId = call.parameters["petId"]?.toIntOrNull()
            ?: throw BadRequestException("Pet ID can't be null or non-numeric")

        val notes = NoteRepository.getNotesByPetId(petId)
        call.respond(HttpStatusCode.OK, notes)
    }
}
