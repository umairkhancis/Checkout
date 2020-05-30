package com.noorifytech.maha.data.dao.mapper

import com.noorifytech.maha.data.dao.dto.ProductEntity
import com.noorifytech.maha.data.dao.impl.db.tables.Products
import org.jetbrains.exposed.sql.ResultRow

object DatabaseDaoMapper {
    fun map(row: ResultRow): ProductEntity =
            ProductEntity(id = row[Products.id],
                    name = row[Products.name],
                    unitPrice = row[Products.unitPrice],
                    discountPrice = row[Products.discountPrice],
                    discountQty = row[Products.discountQty]
            )
}