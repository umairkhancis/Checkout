package com.noorifytech.maha.data.dao.impl.db.impl
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

object H2Database : com.noorifytech.maha.data.dao.impl.db.Database {
    override fun init() {
        Database.connect(hikari())
    }

    override suspend fun <T> query(
            block: (transaction: Transaction) -> T): T =
            withContext(Dispatchers.IO) {
                transaction { block(this) }
            }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.validate()
        return HikariDataSource(config)
    }
}