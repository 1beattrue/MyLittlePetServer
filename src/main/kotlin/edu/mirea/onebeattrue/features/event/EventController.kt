package edu.mirea.onebeattrue.features.event

import edu.mirea.onebeattrue.database.event.EventDto
import edu.mirea.onebeattrue.database.event.EventRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class EventController {

    suspend fun createEvent(call: ApplicationCall) {
        val eventDto = call.receive<EventDto>()
        val id = EventRepository.createEvent(eventDto)
        call.respond(HttpStatusCode.Created, id)
    }

    suspend fun getEventById(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val event = EventRepository.getEventById(id)
        if (event != null) {
            call.respond(HttpStatusCode.OK, event)
        } else {
            call.respond(HttpStatusCode.NotFound, "Event not found")
        }
    }

    suspend fun updateEvent(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")
        val eventDto = call.receive<EventDto>().copy(id = id)

        val updated = EventRepository.updateEvent(eventDto)
        if (updated) {
            call.respond(HttpStatusCode.OK, "Event updated")
        } else {
            call.respond(HttpStatusCode.NotFound, "Event not found")
        }
    }

    suspend fun deleteEvent(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val deleted = EventRepository.deleteEvent(id)
        if (deleted) {
            call.respond(HttpStatusCode.OK, "Event deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "Event not found")
        }
    }

    suspend fun getEventsByPetId(call: ApplicationCall) {
        val petId = call.parameters["petId"]?.toIntOrNull()
            ?: throw BadRequestException("Pet ID can't be null or non-numeric")

        val eventList = EventRepository.getEventsByPetId(petId)
        call.respond(HttpStatusCode.OK, eventList)
    }

    suspend fun deleteIrrelevantEvents(call: ApplicationCall) {
        val petId = call.parameters["petId"]?.toIntOrNull()
            ?: throw BadRequestException("Pet ID can't be null or non-numeric")

        val deletedEvents = EventRepository.deleteIrrelevantEvents(petId)
        call.respond(HttpStatusCode.OK, deletedEvents)
    }
}
