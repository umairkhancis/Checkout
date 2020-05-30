package com.noorifytech.maha.core.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ProductTest {
    @Test
    fun test() {
        val watch = Product("001", "Rolex", 100f)
        Assertions.assertThat(watch.id).isEqualTo("001")
    }
}