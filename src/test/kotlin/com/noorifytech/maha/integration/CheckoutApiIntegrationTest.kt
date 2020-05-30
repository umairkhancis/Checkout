package com.noorifytech.maha.integration

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

class CheckoutApiIntegrationTest : IntegrationTest() {
    @Test
    fun checkoutApi() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "002", "001", "004", "003"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(360))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenAllWatchesEachOne() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "002", "003", "004"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(260))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenOneRolexWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(100))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenOneMichaelKorsWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("002"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(80))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenOneSwatchWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("003"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(50))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenOneCasioWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("004"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(30))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenTwoRolexWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "001"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(200))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenThreeRolexWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "001", "001"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(200))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenFourRolexWatchOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "001", "001", "001"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(300))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenTwoMichaelKorsWatchesOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("002", "002"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(120))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenThreeMichaelKorsWatchesOnly() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("002", "002", "002"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(200))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenThreeRolexAndTwoMichaelKorsWatches() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "001", "001", "002", "002"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(320))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenThreeRolexAndFiveMichaelKorsWatches() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf("001", "001", "001", "002", "002", "002", "002", "002"))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(520))
                .statusCode(200)
    }

    @Test
    fun checkoutApi_whenSevenRolexAndFiveMichaelKorsWatchesAndTwoSwatchThreeCasio() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(listOf(
                        "001", "001", "001", "001", "001", "001", "001",
                        "002", "002", "002", "002", "002",
                        "003", "003",
                        "004", "004", "004"
                ))
                .`when`()
                .post("/checkout")
                .then()
                .body("price", CoreMatchers.equalTo(500+320+100+90))
                .statusCode(200)
    }
}