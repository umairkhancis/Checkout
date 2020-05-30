package com.noorifytech.maha.core.usecase

class CheckoutPriceCalculatorUseCase(private val cart: List<String>) {

    fun calculate(): Float {
        return 360f
    }
}
