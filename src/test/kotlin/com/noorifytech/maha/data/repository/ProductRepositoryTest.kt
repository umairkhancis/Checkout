package com.noorifytech.maha.data.repository

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.dao.DatabaseDao
import com.noorifytech.maha.data.dao.dto.ProductEntity
import com.noorifytech.maha.data.repository.impl.ProductRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class ProductRepositoryTest {

    @Mock
    private lateinit var databaseDao: DatabaseDao
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        productRepository = ProductRepositoryImpl(databaseDao)
    }

    @Test
    fun getProduct_whenProductDoesNotExistWithTheGivenId_returnsNull(): Unit = runBlocking {
        // Arrange
        val productId = "001"
        Mockito.`when`(databaseDao.getProducts(listOf(productId)))
                .thenReturn(listOf())

        // Act
        val product = productRepository.getProduct(productId)

        // Assert
        Assertions.assertThat(product).isEqualTo(null)

        Unit
    }

    @Test
    fun getProduct_whenProductExistsWithTheGivenId_returnsProduct(): Unit = runBlocking {
        // Arrange
        val productId = "001"
        val expectedProduct = Product("001", "Rolex", BigDecimal(100))
        val productEntity = ProductEntity("001", "Rolex", BigDecimal(100))
        Mockito.`when`(databaseDao.getProducts(listOf(productId)))
                .thenReturn(listOf(productEntity))

        // Act
        val product = productRepository.getProduct(productId)

        // Assert
        Assertions.assertThat(product).isEqualTo(expectedProduct)

        Unit
    }

    @Test
    fun getProducts_whenProductsDoesNotExistWithTheGivenId_returnsEmptyList(): Unit = runBlocking {
        // Arrange
        val productIds = listOf("001")
        Mockito.`when`(databaseDao.getProducts(productIds)).thenReturn(listOf())

        // Act
        val products = productRepository.getProducts(productIds)

        // Assert
        Assertions.assertThat(products).isEqualTo(emptyList<Product>())

        Unit
    }

    @Test
    fun getProducts_whenProductsExistsWithTheGivenId_returnsProducts(): Unit = runBlocking {
        // Arrange
        val productIds = listOf("001")
        val productEntities = listOf(ProductEntity("001", "Rolex", BigDecimal(100)))
        val expectedProducts = listOf(Product("001", "Rolex", BigDecimal(100)))
        Mockito.`when`(databaseDao.getProducts(productIds))
                .thenReturn(productEntities)

        // Act
        val products = productRepository.getProducts(productIds)

        // Assert
        Assertions.assertThat(products).isEqualTo(expectedProducts)

        Unit
    }
}