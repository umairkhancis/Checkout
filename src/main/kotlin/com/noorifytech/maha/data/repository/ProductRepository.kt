package com.noorifytech.maha.data.repository

import com.noorifytech.maha.core.domain.Product

interface ProductRepository {
    fun getProduct(id: String): Product
    fun getProducts(ids: List<String>): List<Product>
}