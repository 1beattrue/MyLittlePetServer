package edu.mirea.onebeattrue.features.pet

import edu.mirea.onebeattrue.database.pet.PetDto
import edu.mirea.onebeattrue.database.pet.PetRepository
import edu.mirea.onebeattrue.database.user.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class PetController {

    suspend fun createPet(call: ApplicationCall) {
        val petDto = call.receive<PetDto>()

        val user = UserRepository.getUserByToken(petDto.userToken)
        if (user == null) {
            call.respond(HttpStatusCode.NotFound, "User not found")
        }

        val id = PetRepository.createPet(petDto)
        call.respond(HttpStatusCode.Created, id)
    }

    suspend fun getPetById(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val pet = PetRepository.getPetById(id)
        if (pet != null) {
            call.respond(HttpStatusCode.OK, pet)
        } else {
            call.respond(HttpStatusCode.NotFound, "Pet not found")
        }
    }

    suspend fun updatePet(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")
        val petDto = call.receive<PetDto>().copy(id = id)

        val updated = PetRepository.updatePet(petDto)
        if (updated) {
            call.respond(HttpStatusCode.OK, "Pet updated")
        } else {
            call.respond(HttpStatusCode.NotFound, "Pet not found")
        }
    }

    suspend fun deletePet(call: ApplicationCall) {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw BadRequestException("ID can't be null or non-numeric")

        val deleted = PetRepository.deletePet(id)
        if (deleted) {
            call.respond(HttpStatusCode.OK, "Pet deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "Pet not found")
        }
    }

    suspend fun getPetsByUserToken(call: ApplicationCall) {
        val userToken = call.parameters["userToken"]
            ?: throw BadRequestException("UserToken can't be null")

        val pets = PetRepository.getPetsByUserToken(userToken)
        call.respond(HttpStatusCode.OK, pets)
    }

    suspend fun copyPet(call: ApplicationCall) {
        val petId = call.parameters["petId"]?.toIntOrNull()
        val newUserToken = call.parameters["newUserToken"]

        if (petId == null || newUserToken == null) {
            call.respond(HttpStatusCode.BadRequest, "Pet ID and newUserToken are required")
            return
        }

        try {
            val newPet = PetRepository.copyPet(petId, newUserToken)
            if (newPet != null) {
                call.respond(HttpStatusCode.Created, newPet)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to copy pet")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "An error occurred")
        }
    }
}