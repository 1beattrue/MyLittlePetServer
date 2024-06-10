package edu.mirea.onebeattrue.database.event

import org.jetbrains.exposed.sql.Table

object Events : Table() {
    val id = Events.integer("id").autoIncrement()
    val time = Events.long("time")
    val label = Events.varchar("label", 255)
    val repeatable = Events.bool("repeatable")
    val petId = Events.integer("pet_id")
}