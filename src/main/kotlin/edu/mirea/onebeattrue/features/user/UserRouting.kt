package edu.mirea.onebeattrue.features.user

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureUserRouting() {
    val userController = UserController()

    routing {
        route("api/v1") {
            route("/users") {
                post {
                    userController.createUser(call)
                }

                get("{token}") {
                    userController.getUserByToken(call)
                }

                delete("{token}") {
                    userController.deleteUser(call)
                }
            }
        }
    }
}