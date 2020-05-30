package com.noorifytech.maha.data.dao

import com.noorifytech.maha.data.dao.dto.ProductEntity

interface DatabaseDao {
    suspend fun getProducts(ids: List<String>): List<ProductEntity>
}