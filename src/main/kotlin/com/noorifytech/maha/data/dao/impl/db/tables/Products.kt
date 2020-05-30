package com.noorifytech.maha.data.dao.impl.db.tables

import org.jetbrains.exposed.sql.Table

object Products : Table() {
    val id = varchar("id", length = 255)
    val name = varchar("name", 255)
    val unitPrice = decimal("unitPrice", 15, 0)
    val discountPrice = decimal("discountPrice", 15, 0)
    val discountQty = integer("discountQty")
}
