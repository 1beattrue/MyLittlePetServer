package edu.mirea.onebeattrue.database.pet

import edu.mirea.onebeattrue.database.user.Users
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Pets : Table() {
    val id = Pets.integer("id").autoIncrement()
    val type = Pets.varchar("type", 50)
    val name = Pets.varchar("name", 255)
    val imageUrl = Pets.text("image_url").nullable()
    val dateOfBirth = Pets.long("date_of_birth").nullable()
    val weight = Pets.float("weight").nullable()
    val userToken = Pets.text("user_token").references(Users.token, onDelete = ReferenceOption.CASCADE)
}