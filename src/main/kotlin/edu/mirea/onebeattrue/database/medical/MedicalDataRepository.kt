package edu.mirea.onebeattrue.database.medical

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object MedicalDataRepository {

    fun createMedicalData(data: MedicalDataDto): Int {
        val insertStatement = transaction {
            MedicalData.insert {
                it[type] = data.type
                it[imageUrl] = data.imageUrl
                it[text] = data.text
                it[petId] = data.petId
            }
        }

        return insertStatement.resultedValues?.first()?.get(MedicalData.id)
            ?: throw IllegalStateException("Failed to insert Medical data")
    }

    fun getMedicalDataById(id: Int): MedicalDataDto? {
        return transaction {
            MedicalData.selectAll().where { MedicalData.id eq id }
                .map {
                    MedicalDataDto(
                        id = it[MedicalData.id],
                        type = it[MedicalData.type],
                        imageUrl = it[MedicalData.imageUrl],
                        text = it[MedicalData.text],
                        petId = it[MedicalData.petId]
                    )
                }
                .singleOrNull()
        }
    }

    fun updateMedicalData(data: MedicalDataDto): Boolean {
        return transaction {
            MedicalData.update({ MedicalData.id eq data.id }) {
                it[type] = data.type
                it[imageUrl] = data.imageUrl
                it[text] = data.text
                it[petId] = data.petId
            } > 0
        }
    }

    fun deleteMedicalData(id: Int): Boolean {
        return transaction {
            MedicalData.deleteWhere { MedicalData.id eq id } > 0
        }
    }

    fun getMedicalDataByPetId(petId: Int): List<MedicalDataDto> {
        return transaction {
            MedicalData.selectAll().where { MedicalData.petId eq petId }
                .map {
                    MedicalDataDto(
                        id = it[MedicalData.id],
                        type = it[MedicalData.type],
                        imageUrl = it[MedicalData.imageUrl],
                        text = it[MedicalData.text],
                        petId = it[MedicalData.petId]
                    )
                }
        }
    }
}