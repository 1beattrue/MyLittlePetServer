package edu.mirea.onebeattrue.database.medical

import org.jetbrains.exposed.sql.Table

object MedicalData : Table() {
    private val id = MedicalData.integer("id").autoIncrement()
    private val type = MedicalData.varchar("type", 50)
    private val imageUrl = MedicalData.text("image_url").nullable()
    private val text = MedicalData.text("image_url")
    private val petId = MedicalData.integer("pet_id")
}