package com.noorifytech.maha.data.repository.impl

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {
    override fun getProduct(id: String): Product {
        return Product("001", "Rolex", 100f)
    }

    override fun getProducts(ids: List<String>): List<Product> {
        return emptyList()
    }
}