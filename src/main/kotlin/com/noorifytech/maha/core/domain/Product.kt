package com.noorifytech.maha.core.domain

data class Product(
        val id: String,
        val name: String,
        val unitPrice: Float,
        val discountPrice: Float = unitPrice,
        val discountQty: Int = 1
)