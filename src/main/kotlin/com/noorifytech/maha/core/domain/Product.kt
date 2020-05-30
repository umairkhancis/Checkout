package com.noorifytech.maha.core.domain

import java.math.BigDecimal

data class Product(
        val id: String,
        val name: String,
        val unitPrice: BigDecimal,
        val discountPrice: BigDecimal = unitPrice,
        val discountQty: Int = 1
)