package edu.mirea.onebeattrue.config

import com.typesafe.config.ConfigFactory
import org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    fun connect() {
        val config = ConfigFactory.load().getConfig("database")
        Database.connect(
            driver = config.getString("driver"),
            url = config.getString("url"),
            user = config.getString("user"),
            password = config.getString("password")
        )
    }
}