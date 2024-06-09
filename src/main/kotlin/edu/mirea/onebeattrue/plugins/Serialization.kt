package edu.mirea.onebeattrue.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val text: String
)

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
