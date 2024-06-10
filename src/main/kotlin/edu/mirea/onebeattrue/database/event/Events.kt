package edu.mirea.onebeattrue.database.event

import org.jetbrains.exposed.sql.Table

object Events : Table() {
    private val id = Events.integer("id").autoIncrement()
    private val time = Events.long("time")
    private val label = Events.varchar("label", 255)
    private val repeatable = Events.bool("repeatable")
    private val petId = Events.integer("pet_id")
}