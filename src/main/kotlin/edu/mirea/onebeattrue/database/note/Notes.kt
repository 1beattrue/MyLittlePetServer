package edu.mirea.onebeattrue.database.note

import edu.mirea.onebeattrue.database.pet.Pets
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    val id = Notes.integer("id").autoIncrement()
    val text = Notes.text("text")
    val iconResId = Notes.integer("icon_res_id")
    val petId = Notes.integer("pet_id").references(Pets.id, onDelete = ReferenceOption.CASCADE)
}