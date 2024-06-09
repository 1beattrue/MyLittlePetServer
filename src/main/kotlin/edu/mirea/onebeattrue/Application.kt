package edu.mirea.onebeattrue

import edu.mirea.onebeattrue.config.DatabaseConfig
import edu.mirea.onebeattrue.features.user.configureUserRouting
import edu.mirea.onebeattrue.plugins.configureRouting
import edu.mirea.onebeattrue.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    DatabaseConfig.connect()

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()

    configureUserRouting()
}
