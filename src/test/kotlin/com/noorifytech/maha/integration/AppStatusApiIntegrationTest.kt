package com.noorifytech.maha.integration

import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class AppStatusApiIntegrationTest : IntegrationTest() {
    @Test
    fun applicationStatusApi() {
        get("/")
                .then()
                .body(CoreMatchers.equalTo("Maha e-commerce application is up and running..."))
                .statusCode(200)
    }
}