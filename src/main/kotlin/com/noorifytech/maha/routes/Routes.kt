package com.noorifytech.maha.routes

import com.noorifytech.maha.dto.Price
import com.noorifytech.maha.dto.Response
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.checkout() {
    route("/") {
        get {
            call.respond("Maha e-commerce application is up and running...")
        }
    }

    route("/checkout") {
        get {
            call.respond(Price(0f))
        }
    }
}
