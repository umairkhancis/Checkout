package com.noorifytech.maha.core.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductTest {
    @Test
    fun creation() {
        val watch = Product("001", "Rolex", BigDecimal(100))
        Assertions.assertThat(watch.id).isEqualTo("001")
    }
}