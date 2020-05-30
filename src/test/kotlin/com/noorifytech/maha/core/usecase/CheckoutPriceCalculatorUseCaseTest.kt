package com.noorifytech.maha.core.usecase

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.repository.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CheckoutPriceCalculatorUseCaseTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun calculate_whenOneItemEach() {
        // Arrange
        val cart = listOf("001", "002", "003", "004")
        Mockito.`when`(productRepository.getProducts(cart)).thenReturn(getProducts())

        // Act
        val price = CheckoutPriceCalculatorUseCase(cart, productRepository).calculate()

        // Assert
        Assertions.assertThat(price).isEqualTo(260f)
    }

    @Test
    fun calculate_whenDiscountAppliesForOneItemOnly() {
        // Arrange
        val cart = listOf("001", "002", "002", "004", "003")
        Mockito.`when`(productRepository.getProducts(cart)).thenReturn(getProducts())

        // Act
        val price = CheckoutPriceCalculatorUseCase(cart, productRepository).calculate()

        // Assert
        Assertions.assertThat(price).isEqualTo(340f)
    }

    private fun getProducts(): List<Product> {
        return listOf(
                Product("001", "Rolex", 100f),
                Product("002", "Michael Kors", 80f),
                Product("003", "Swatch", 50f),
                Product("004", "Casio", 30f)
        )
    }
}