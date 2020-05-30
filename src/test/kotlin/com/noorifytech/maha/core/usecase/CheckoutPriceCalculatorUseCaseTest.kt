package com.noorifytech.maha.core.usecase

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CheckoutPriceCalculatorUseCaseTest {

    @Test
    fun calculate() {
        // Arrange
        val cart = listOf("001", "002", "001", "004", "003")

        // Act
        val price = CheckoutPriceCalculatorUseCase(cart).calculate()

        // Assert
        Assertions.assertThat(price).isEqualTo(360f)
    }
}