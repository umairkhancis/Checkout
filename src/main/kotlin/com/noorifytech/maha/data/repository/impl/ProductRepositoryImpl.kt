package com.noorifytech.maha.data.repository.impl

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.dao.DatabaseDao
import com.noorifytech.maha.data.mapper.ProductDomainMapper
import com.noorifytech.maha.data.repository.ProductRepository

class ProductRepositoryImpl(private val databaseDao: DatabaseDao) : ProductRepository {
    override suspend fun getProduct(id: String): Product? {
        return databaseDao.getProducts(listOf(id))
                .map { ProductDomainMapper.map(it) }
                .firstOrNull()
    }

    override suspend fun getProducts(ids: List<String>): List<Product> {
        return databaseDao.getProducts(ids.distinct())
                .map { ProductDomainMapper.map(it) }
    }
}