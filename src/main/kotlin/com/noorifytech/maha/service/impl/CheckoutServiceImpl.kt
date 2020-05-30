package com.noorifytech.maha.service.impl

import com.noorifytech.maha.core.usecase.CheckoutPriceCalculatorUseCase
import com.noorifytech.maha.core.usecase.impl.CheckoutPriceCalculatorUseCaseImpl
import com.noorifytech.maha.dto.Price
import com.noorifytech.maha.service.CheckoutService

class CheckoutServiceImpl(
        private val checkoutPriceCalculatorUseCase: CheckoutPriceCalculatorUseCase = CheckoutPriceCalculatorUseCaseImpl()
) : CheckoutService {
    override suspend fun checkout(cart: List<String>): Price {
        return Price(checkoutPriceCalculatorUseCase.calculate(cart))
    }
}