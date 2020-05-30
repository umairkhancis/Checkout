package com.noorifytech.maha.service

import com.noorifytech.maha.dto.Price

interface CheckoutService {
    suspend fun checkout(cart: List<String>): Price
}