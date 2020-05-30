package com.noorifytech.maha.core.usecase

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.repository.ProductRepository
import com.noorifytech.maha.data.repository.impl.ProductRepositoryImpl

class CheckoutPriceCalculatorUseCase(
        private val cart: List<String>,
        private val productRepository: ProductRepository = ProductRepositoryImpl()
) {

    fun calculate(): Float {
        var totalPrice = 0f
        val products = productRepository.getProducts(cart)

        val itemCountMap = itemCountMap(cart)
        val productDetailsMap = productDetailsMap(products)

        val discountedProducts = products.filter { validDiscountConfig(it) }

        for (itemId in itemCountMap.keys) {
            val product = productDetailsMap[itemId]
            val itemCount = itemCountMap[itemId] ?: 0

            product?.let {
                totalPrice += if (product in discountedProducts && itemCount >= product.discountQty) {
                    val discountedCount = itemCount / product.discountQty
                    val nonDiscountedCount = itemCount % product.discountQty
                    product.discountPrice.times(discountedCount) + product.unitPrice.times(nonDiscountedCount)
                } else {
                    product.unitPrice.times(itemCount.toFloat())
                }
            }
        }

        return totalPrice
    }

    private fun validDiscountConfig(it: Product) =
            it.discountPrice != it.unitPrice && it.discountQty > 1

    private fun itemCountMap(itemIds: List<String>): HashMap<String, Int> {
        val map = mutableMapOf<String, Int>()

        for (itemId in itemIds) {
            map[itemId] = map[itemId]?.plus(1) ?: 1
        }

        return map as HashMap<String, Int>
    }

    private fun productDetailsMap(products: List<Product>): HashMap<String, Product> {
        val map = mutableMapOf<String, Product>()

        for (product in products) {
            map[product.id] = product
        }

        return map as HashMap<String, Product>
    }
}
