package edu.mirea.onebeattrue

import edu.mirea.onebeattrue.config.DatabaseConfig
import edu.mirea.onebeattrue.database.configureDatabase
import edu.mirea.onebeattrue.plugins.configureRouting
import edu.mirea.onebeattrue.plugins.configureSerialization
import edu.mirea.onebeattrue.plugins.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080

    embeddedServer(
        Netty,
        port = port,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    DatabaseConfig.connect()
    configureDatabase()

    configureSerialization()
    configureRouting()
    configureStatusPages()
}
