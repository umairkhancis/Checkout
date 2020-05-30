package com.noorifytech.maha.service

import com.noorifytech.maha.core.usecase.CheckoutPriceCalculatorUseCase
import com.noorifytech.maha.dto.Price
import com.noorifytech.maha.service.impl.CheckoutServiceImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class CheckoutServiceTest {

    @Mock
    private lateinit var checkoutPriceCalculatorUseCase: CheckoutPriceCalculatorUseCase
    private lateinit var checkoutService: CheckoutService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        checkoutService = CheckoutServiceImpl(checkoutPriceCalculatorUseCase)
    }

    @Test
    fun test() = runBlocking {
        // Arrange
        val cart = listOf("001", "001", "001")
        Mockito.`when`(checkoutPriceCalculatorUseCase.calculate(cart))
                .thenReturn(BigDecimal(200))
        val expectedPrice = Price(BigDecimal(200))

        // Act
        val price = checkoutService.checkout(cart)

        // Assert
        Assertions.assertThat(price).isEqualTo(expectedPrice)

        Unit
    }
}