package edu.mirea.onebeattrue.database.pet

import org.jetbrains.exposed.sql.Table

object Pets : Table() {
    private val id = Pets.integer("id").autoIncrement()
    private val type = Pets.varchar("type", 50)
    private val name = Pets.varchar("name", 100)
    private val imageUrl = Pets.text("image_url").nullable()
    private val dateOfBirth = Pets.long("date_of_birth").nullable()
    private val weight = Pets.float("weight").nullable()


}