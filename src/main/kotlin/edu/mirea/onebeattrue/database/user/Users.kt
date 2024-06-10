package edu.mirea.onebeattrue.database.user

import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val token = text("token")
}