package com.noorifytech.maha.data.repository

import com.noorifytech.maha.core.domain.Product

interface ProductRepository {
    suspend fun getProduct(id: String): Product?
    suspend fun getProducts(ids: List<String>): List<Product>
}