package edu.mirea.onebeattrue.plugins

import edu.mirea.onebeattrue.features.pet.PetController
import edu.mirea.onebeattrue.features.user.UserController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("Hello World!")
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
    }
}
