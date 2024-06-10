package edu.mirea.onebeattrue.database.note

import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    private val id = Notes.integer("id").autoIncrement()
    private val text = Notes.text("text")
    private val iconResId = Notes.integer("icon_res_id")
    private val petId = Notes.integer("pet_id")
}