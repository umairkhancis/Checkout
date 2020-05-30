package com.noorifytech.maha.integration

import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class CheckoutApiIntegrationTest : IntegrationTest() {
    @Test
    fun checkoutApi() {
        get("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(0f))
                .statusCode(200)
    }
}