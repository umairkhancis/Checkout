package com.noorifytech.maha.data.mapper

import com.noorifytech.maha.core.domain.Product
import com.noorifytech.maha.data.dao.dto.ProductEntity

object ProductDomainMapper {
    fun map(productEntity: ProductEntity): Product =
            Product(id = productEntity.id,
                    name = productEntity.name,
                    unitPrice = productEntity.unitPrice,
                    discountPrice = productEntity.discountPrice,
                    discountQty = productEntity.discountQty
            )
}