package com.noorifytech.maha.core.usecase.impl

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.core.usecase.CheckoutPriceCalculatorUseCase
import com.noorifytech.maha.core.usecase.factory.DaoFactory
import com.noorifytech.maha.data.repository.ProductRepository
import com.noorifytech.maha.data.repository.impl.ProductRepositoryImpl
import java.math.BigDecimal

class CheckoutPriceCalculatorUseCaseImpl(
    private val productRepository: ProductRepository = ProductRepositoryImpl(DaoFactory.getDatabaseDao())
) : CheckoutPriceCalculatorUseCase {

    override suspend fun calculate(cart: List<String>): BigDecimal {
        var totalPrice = BigDecimal(0)
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
                    product.discountPrice.times(discountedCount.toBigDecimal())
                            .add(product.unitPrice.times(nonDiscountedCount.toBigDecimal()))
                } else {
                    product.unitPrice.times(itemCount.toBigDecimal())
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
