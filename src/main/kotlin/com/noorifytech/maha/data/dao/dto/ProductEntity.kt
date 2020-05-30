package com.noorifytech.maha.data.dao.dto

import java.math.BigDecimal

/**
 * Database Entity Dto
 */
data class ProductEntity(
        val id: String,
        val name: String,
        val unitPrice: BigDecimal,
        val discountPrice: BigDecimal = unitPrice,
        val discountQty: Int = 1
)