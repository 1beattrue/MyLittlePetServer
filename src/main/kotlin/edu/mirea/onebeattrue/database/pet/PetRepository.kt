package edu.mirea.onebeattrue.database.pet

import edu.mirea.onebeattrue.database.medical.MedicalDataRepository
import edu.mirea.onebeattrue.database.pet.Pets.dateOfBirth
import edu.mirea.onebeattrue.database.pet.Pets.imageUrl
import edu.mirea.onebeattrue.database.pet.Pets.name
import edu.mirea.onebeattrue.database.pet.Pets.type
import edu.mirea.onebeattrue.database.pet.Pets.userToken
import edu.mirea.onebeattrue.database.pet.Pets.weight
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object PetRepository {

    fun createPet(pet: PetDto): Int {
        val insertStatement = transaction {
            Pets.insert {
                it[type] = pet.type
                it[name] = pet.name
                it[imageUrl] = pet.imageUrl
                it[dateOfBirth] = pet.dateOfBirth
                it[weight] = pet.weight
                it[userToken] = pet.userToken
            }
        }

        return insertStatement.resultedValues?.first()?.get(Pets.id)
            ?: throw IllegalStateException("Failed to insert Pet")
    }

    fun getPetById(id: Int): PetDto? {
        return transaction {
            Pets.selectAll().where { Pets.id eq id }
                .map {
                    PetDto(
                        id = it[Pets.id],
                        type = it[type],
                        name = it[name],
                        imageUrl = it[imageUrl],
                        dateOfBirth = it[dateOfBirth],
                        weight = it[weight],
                        userToken = it[userToken]
                    )
                }
                .singleOrNull()
        }
    }

    fun updatePet(pet: PetDto): Boolean {
        return transaction {
            Pets.update({ Pets.id eq pet.id }) {
                it[type] = pet.type
                it[name] = pet.name
                it[imageUrl] = pet.imageUrl
                it[dateOfBirth] = pet.dateOfBirth
                it[weight] = pet.weight
                it[userToken] = pet.userToken
            } > 0
        }
    }

    fun deletePet(id: Int): Boolean {
        return transaction {
            Pets.deleteWhere { Pets.id eq id } > 0
        }
    }

    fun getPetsByUserToken(userToken: String): List<PetDto> {
        return transaction {
            Pets.selectAll().where { Pets.userToken eq userToken }
                .map {
                    PetDto(
                        id = it[Pets.id],
                        type = it[type],
                        name = it[name],
                        imageUrl = it[imageUrl],
                        dateOfBirth = it[dateOfBirth],
                        weight = it[weight],
                        userToken = it[Pets.userToken]
                    )
                }
        }
    }

    fun copyPet(petId: Int, newUserToken: String): PetDto? {
        return transaction {
            val pet = getPetById(petId) ?: throw IllegalArgumentException("Pet not found")

            val newPetId = createPet(
                pet.copy(
                    id = 0,
                    userToken = newUserToken
                )
            )

            val medicalData = MedicalDataRepository.getMedicalDataByPetId(pet.id)
            medicalData.forEach { data ->
                MedicalDataRepository.createMedicalData(
                    data.copy(
                        id = PetDto.UNDEFINED_ID,
                        petId = newPetId
                    )
                )
            }

            getPetById(newPetId)
        }
    }
}
