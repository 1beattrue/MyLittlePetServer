package edu.mirea.onebeattrue.database.event

import edu.mirea.onebeattrue.database.pet.Pets
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Events : Table() {
    val id = Events.integer("id").uniqueIndex().autoIncrement()
    val time = Events.long("time")
    val label = Events.varchar("label", 255)
    val repeatable = Events.bool("repeatable")
    val petId = Events.integer("pet_id").references(Pets.id, onDelete = ReferenceOption.CASCADE)
}