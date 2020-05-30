package com.noorifytech.maha

import com.fasterxml.jackson.databind.SerializationFeature
import com.noorifytech.maha.data.dao.impl.db.impl.H2Database
import com.noorifytech.maha.routes.checkout
import com.noorifytech.maha.service.impl.CheckoutServiceImpl
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)

    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }

    H2Database.init()

    install(Routing) {
        checkout(CheckoutServiceImpl())
    }

}

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            embeddedServer(
                    factory = Netty,
                    port = 8080,
                    watchPaths = listOf("Main"),
                    module = Application::module
            ).start(wait = true)
        }
    }
}