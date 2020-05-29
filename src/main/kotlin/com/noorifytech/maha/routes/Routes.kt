package com.noorifytech.maha.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.checkout() {
    route("/") {
        get {
            call.respond("Hello World")
        }
    }
}
