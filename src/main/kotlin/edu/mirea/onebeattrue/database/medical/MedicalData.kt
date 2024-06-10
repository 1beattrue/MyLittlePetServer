package edu.mirea.onebeattrue.database.medical

import org.jetbrains.exposed.sql.Table

object MedicalData : Table() {
    val id = MedicalData.integer("id").autoIncrement()
    val type = MedicalData.varchar("type", 50)
    val imageUrl = MedicalData.text("image_url").nullable()
    val text = MedicalData.text("image_url")
    val petId = MedicalData.integer("pet_id")
}