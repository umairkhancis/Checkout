package com.noorifytech.maha.core.usecase

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.core.usecase.impl.CheckoutPriceCalculatorUseCaseImpl
import com.noorifytech.maha.data.repository.ProductRepository
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class CheckoutPriceCalculatorUseCaseTest {

    @Mock
    private lateinit var productRepository: ProductRepository
    private lateinit var checkoutPriceCalculatorUseCase: CheckoutPriceCalculatorUseCase

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        checkoutPriceCalculatorUseCase = CheckoutPriceCalculatorUseCaseImpl(productRepository)
    }

    @Test
    fun calculate_whenOnlyOneItem(): Unit = runBlocking {
        // Arrange
        val cart = listOf("001")
        Mockito.`when`(productRepository.getProducts(cart))
                .thenReturn(listOf(Product("001", "Rolex", BigDecimal(100))))

        // Act
        val price = checkoutPriceCalculatorUseCase.calculate(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(BigDecimal(100))

        Unit
    }

    @Test
    fun calculate_whenDiscountedItemIsLessThanDiscountedQuantity(): Unit = runBlocking {
        // Arrange
        val cart = listOf("001", "002")
        Mockito.`when`(productRepository.getProducts(cart))
                .thenReturn(listOf(
                        Product("001", "Rolex", BigDecimal(100)),
                        Product("002", "Michael Kors", BigDecimal(80), discountPrice = BigDecimal(120), discountQty = 2))
                )

        // Act
        val price = checkoutPriceCalculatorUseCase.calculate(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(BigDecimal(180))

        Unit
    }

    @Test
    fun calculate_whenOneItemEach(): Unit = runBlocking {
        // Arrange
        val cart = listOf("001", "002", "003", "004")
        Mockito.`when`(productRepository.getProducts(cart)).thenReturn(getProducts())

        // Act
        val price = checkoutPriceCalculatorUseCase.calculate(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(BigDecimal(260))

        Unit
    }

    @Test
    fun calculate_whenDiscountAppliesForOneItemOnly(): Unit = runBlocking {
        // Arrange
        val cart = listOf("001", "002", "002", "004", "003")
        Mockito.`when`(productRepository.getProducts(cart)).thenReturn(getProducts())

        // Act
        val price = checkoutPriceCalculatorUseCase.calculate(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(BigDecimal(300))

        Unit
    }

    @Test
    fun calculate_whenDiscountAppliesForTwoItems(): Unit = runBlocking {
        // Arrange
        val cart = listOf("001", "001", "001", "002", "002", "004", "003")
        Mockito.`when`(productRepository.getProducts(cart)).thenReturn(getProducts())

        // Act
        val price = checkoutPriceCalculatorUseCase.calculate(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(BigDecimal(400))

        Unit
    }

    private fun getProducts(): List<Product> {
        return listOf(
                Product("001", "Rolex", BigDecimal(100), discountPrice = BigDecimal(200), discountQty = 3),
                Product("002", "Michael Kors", BigDecimal(80), discountPrice = BigDecimal(120), discountQty = 2),
                Product("003", "Swatch", BigDecimal(50)),
                Product("004", "Casio", BigDecimal(30))
        )
    }
}