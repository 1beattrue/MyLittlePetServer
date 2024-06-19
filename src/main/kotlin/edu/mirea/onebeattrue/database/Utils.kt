package edu.mirea.onebeattrue.database

import edu.mirea.onebeattrue.database.event.Events
import edu.mirea.onebeattrue.database.medical.MedicalData
import edu.mirea.onebeattrue.database.note.Notes
import edu.mirea.onebeattrue.database.pet.Pets
import edu.mirea.onebeattrue.database.user.Users
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            Users,
            Pets,
            Events,
            Notes,
            MedicalData
        )
    }
}