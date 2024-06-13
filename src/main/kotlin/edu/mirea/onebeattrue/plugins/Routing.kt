package edu.mirea.onebeattrue.plugins

import edu.mirea.onebeattrue.features.event.EventController
import edu.mirea.onebeattrue.features.medicaldata.MedicalDataController
import edu.mirea.onebeattrue.features.note.NoteController
import edu.mirea.onebeattrue.features.pet.PetController
import edu.mirea.onebeattrue.features.user.UserController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("My Little Pet Server")
        }

        route("/users") {
            post("/create") { UserController().createUser(call) }
            get("/{token}") { UserController().getUserByToken(call) }
            delete("/{token}") { UserController().deleteUser(call) }
        }

        route("/pets") {
            post("/create") { PetController().createPet(call) }
            get("/{id}") { PetController().getPetById(call) }
            put("/{id}") { PetController().updatePet(call) }
            delete("/{id}") { PetController().deletePet(call) }
            get("/byUserToken/{userToken}") { PetController().getPetsByUserToken(call) }
        }

        route("/notes") {
            post("/create") { NoteController().createNote(call) }
            get("/{id}") { NoteController().getNoteById(call) }
            put("/{id}") { NoteController().updateNote(call) }
            delete("/{id}") { NoteController().deleteNote(call) }
            get("/byPetId/{petId}") { NoteController().getNotesByPetId(call) }
        }

        route("/medicalData") {
            post("/create") { MedicalDataController().createMedicalData(call) }
            get("/{id}") { MedicalDataController().getMedicalDataById(call) }
            put("/{id}") { MedicalDataController().updateMedicalData(call) }
            delete("/{id}") { MedicalDataController().deleteMedicalData(call) }
            get("/byPetId/{petId}") { MedicalDataController().getMedicalDataByPetId(call) }
        }

        route("/events") {
            post("/create") { EventController().createEvent(call) }
            get("/{id}") { EventController().getEventById(call) }
            put("/{id}") { EventController().updateEvent(call) }
            delete("/{id}") { EventController().deleteEvent(call) }
            get("/byPetId/{petId}") { EventController().getEventsByPetId(call) }
        }
    }
}
