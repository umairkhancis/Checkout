package com.noorifytech.maha.routes

import com.noorifytech.maha.service.CheckoutService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.checkout(checkoutService: CheckoutService) {
    route("/") {
        get {
            call.respond("Maha e-commerce application is up and running...")
        }
    }

    route("/checkout") {
        post {
            val cart = call.receive<List<String>>()
            call.respond(checkoutService.checkout(cart))
        }
    }
}
