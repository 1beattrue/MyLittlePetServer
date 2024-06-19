package edu.mirea.onebeattrue.database.medical

import edu.mirea.onebeattrue.database.pet.Pets
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object MedicalData : Table() {
    val id = MedicalData.integer("id").uniqueIndex().autoIncrement()
    val type = MedicalData.varchar("type", 50)
    val imageUrl = MedicalData.text("image_url").nullable()
    val text = MedicalData.text("text")
    val petId = MedicalData.integer("pet_id").references(Pets.id, onDelete = ReferenceOption.CASCADE)
}