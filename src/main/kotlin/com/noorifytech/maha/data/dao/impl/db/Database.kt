package com.noorifytech.maha.data.dao.impl.db

import org.jetbrains.exposed.sql.Transaction

interface Database {
    fun init()

    suspend fun <T> query(
            block: (transaction: Transaction) -> T): T
}