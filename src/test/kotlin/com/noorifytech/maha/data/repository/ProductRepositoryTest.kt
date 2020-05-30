package com.noorifytech.maha.data.repository

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.repository.impl.ProductRepositoryImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductRepositoryTest {

    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        productRepository = ProductRepositoryImpl()
    }

    @Test
    fun getProduct() {
        // Arrange
        val productId = "001"

        // Act
        val product = productRepository.getProduct(productId)

        // Assert
        Assertions.assertThat(product.id).isEqualTo(productId)
    }

    @Test
    fun getProducts() {
        // Arrange
        val productIds = listOf("001")

        // Act
        val products = productRepository.getProducts(productIds)

        // Assert
        Assertions.assertThat(products).isEqualTo(emptyList<Product>())
    }
}