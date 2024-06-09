package edu.mirea.onebeattrue.database.pet

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Pets : Table() {
    private val id = Pets.integer("id").autoIncrement()
    private val type = Pets.varchar("type", 50)
    private val name = Pets.varchar("name", 100)
    private val imageUrl = Pets.text("image_url").nullable()
    private val dateOfBirth = Pets.long("date_of_birth").nullable()
    private val weight = Pets.float("weight").nullable()

    fun insert(petDto: PetDto) {
        transaction {
            Pets.insert {
                it[id] = petDto.id
                it[type] = petDto.type
                it[name] = petDto.name
                it[imageUrl] = petDto.imageUrl
                it[dateOfBirth] = petDto.dateOfBirth
                it[weight] = petDto.weight
            }
        }
    }

    fun getById(id: Int): PetDto {
        val petModel = Pets.selectAll().where { Pets.id.eq(id) }.single()
        return PetDto(
            id = id,
            type = petModel[type],
            name = petModel[name],
            imageUrl = petModel[imageUrl],
            dateOfBirth = petModel[dateOfBirth],
            weight = petModel[weight]
        )
    }
}