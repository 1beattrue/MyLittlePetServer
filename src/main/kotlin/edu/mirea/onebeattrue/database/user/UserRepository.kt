package edu.mirea.onebeattrue.database.user

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UserRepository {
    fun createUser(user: UserDto) {
        transaction {
            Users.insert {
                it[token] = user.token
            }
        }
    }

    fun getUserByToken(token: String): UserDto? {
        return transaction {
            Users.selectAll().where { Users.token eq token }
                .map {
                    UserDto(
                        token = it[Users.token]
                    )
                }
                .singleOrNull()
        }
    }

    fun deleteUser(token: String): Boolean {
        return transaction {
            Users.deleteWhere { Users.token eq token } > 0
        }
    }
}
