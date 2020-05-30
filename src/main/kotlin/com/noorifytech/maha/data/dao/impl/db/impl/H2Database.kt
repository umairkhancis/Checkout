package com.noorifytech.maha.data.dao.impl.db.impl
import com.noorifytech.maha.data.dao.impl.db.tables.Products
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal

object H2Database : com.noorifytech.maha.data.dao.impl.db.Database {
    override fun init() {
        Database.connect(hikari())
        transaction {
            createTables()
            insertInitialData()
        }
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

    private fun createTables() {
        create(Products)
    }

    private fun insertInitialData(): InsertStatement<Number> {
        return insertProductsData()
    }

    private fun insertProductsData(): InsertStatement<Number> {
        Products.insert {
            it[id] = "001"
            it[name] = "Rolex"
            it[unitPrice] = BigDecimal(100)
            it[discountPrice] = BigDecimal(200)
            it[discountQty] = 3
        }
        Products.insert {
            it[id] = "002"
            it[name] = "Michael Kors"
            it[unitPrice] = BigDecimal(80)
            it[discountPrice] = BigDecimal(120)
            it[discountQty] = 2
        }
        Products.insert {
            it[id] = "003"
            it[name] = "Swatch"
            it[unitPrice] = BigDecimal(50)
            it[discountPrice] = BigDecimal(50)
            it[discountQty] = 1
        }
        return Products.insert {
            it[id] = "004"
            it[name] = "Casio"
            it[unitPrice] = BigDecimal(30)
            it[discountPrice] = BigDecimal(50)
            it[discountQty] = 1
        }
    }
}