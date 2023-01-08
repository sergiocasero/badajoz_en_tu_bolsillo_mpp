package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.utils.BASIC_AUTH_PASSWORD
import com.badajoz.badajozentubolsillo.utils.BASIC_AUTH_USER
import com.badajoz.badajozentubolsillo.utils.BuildType
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private fun buildClient(endpoint: String, buildType: BuildType, block: HttpClientConfig<*>.() -> Unit = {}):
        HttpClient =
    HttpClient {
        defaultRequest {
            val endpointUrlBuilder = URLBuilder(endpoint)
            url {
                protocol = endpointUrlBuilder.protocol
                host = endpointUrlBuilder.host
            }

            headers.append("Content-Type", "application/json")
        }
        if (buildType != BuildType.PRO) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = true
            })
        }
        block(this)
    }

fun buildClientWithAuth(endpoint: String, buildType: BuildType): HttpClient =
    buildClient(endpoint, buildType) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = BASIC_AUTH_USER,
                        password = BASIC_AUTH_PASSWORD
                    )
                }
                sendWithoutRequest { true }
            }
        }
    }