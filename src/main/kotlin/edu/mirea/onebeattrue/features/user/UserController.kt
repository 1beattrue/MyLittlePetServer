package edu.mirea.onebeattrue.features.user

import edu.mirea.onebeattrue.database.user.UserDto
import edu.mirea.onebeattrue.database.user.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController {

    suspend fun createUser(call: ApplicationCall) {
        val userDto = call.receive<UserDto>()

        val isUserExists = UserRepository.getUserByToken(userDto.token) != null
        if (isUserExists) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            UserRepository.createUser(userDto)
            call.respond(HttpStatusCode.Created, "User created")
        }
    }

    suspend fun getUserByToken(call: ApplicationCall) {
        val token = call.parameters["token"] ?: throw BadRequestException("Token can't be null")

        val user = UserRepository.getUserByToken(token)
        if (user != null) {
            call.respond(HttpStatusCode.OK, user)
        } else {
            call.respond(HttpStatusCode.NotFound, "User not found")
        }
    }

    suspend fun deleteUser(call: ApplicationCall) {
        val token = call.parameters["token"] ?: throw BadRequestException("Token can't be null")

        val deleted = UserRepository.deleteUser(token)
        if (deleted) {
            call.respond(HttpStatusCode.OK, "User deleted")
        } else {
            call.respond(HttpStatusCode.NotFound, "User not found")
        }
    }
}