package com.noorifytech.maha.integration

import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class CheckoutApiIntegrationTest : IntegrationTest() {
    @Test
    fun checkoutApi() {
        get("/checkout")
                .then()
                .body("data.price", CoreMatchers.equalTo(0f))
                .body("code", CoreMatchers.equalTo(200))
                .body("msg", CoreMatchers.equalTo("OK"))
                .statusCode(200)
    }
}