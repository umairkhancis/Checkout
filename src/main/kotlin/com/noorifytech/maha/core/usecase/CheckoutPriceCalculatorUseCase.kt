package com.noorifytech.maha.core.usecase

import java.math.BigDecimal

interface CheckoutPriceCalculatorUseCase {
    suspend fun calculate(cart: List<String>): BigDecimal
}
