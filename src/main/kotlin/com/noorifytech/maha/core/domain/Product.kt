package com.noorifytech.maha.core.domain

data class Product(
        val id: String,
        val name: String,
        val unitPrice: Float,
        val discountPrice: Float = 0f,
        val discountQty: Int = 0
)