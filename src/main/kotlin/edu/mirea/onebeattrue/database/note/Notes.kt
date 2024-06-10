package edu.mirea.onebeattrue.database.note

import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    val id = Notes.integer("id").autoIncrement()
    val text = Notes.text("text")
    val iconResId = Notes.integer("icon_res_id")
    val petId = Notes.integer("pet_id")
}