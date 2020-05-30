package com.noorifytech.maha.data.dao.impl

import com.noorifytech.maha.data.dao.DatabaseDao
import com.noorifytech.maha.data.dao.dto.ProductEntity
import com.noorifytech.maha.data.dao.impl.db.Database
import com.noorifytech.maha.data.dao.impl.db.tables.Products
import com.noorifytech.maha.data.dao.mapper.DatabaseDaoMapper
import org.jetbrains.exposed.sql.selectAll

class H2DatabaseDao(
        private val db: Database,
        private val databaseDaoMapper: DatabaseDaoMapper
) : DatabaseDao {

    override suspend fun getProducts(ids: List<String>): List<ProductEntity> {
        return db.query {
            Products.selectAll().map { databaseDaoMapper.map(it) }
        }
    }
}