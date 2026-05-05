package com.pokedex.data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun createHttpClient(): HttpClient = buildHttpClient(OkHttp.create())