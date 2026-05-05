package com.pokedex.data.remote

import com.pokedex.data.model.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.*
import io.ktor.client.statement.*

class PostApi(private val client: HttpClient) {

    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getPosts(
        page: Int,
        limit: Int,
        userId: Int? = null
    ): Result<List<Post>> {
        return try {
            val response = client.get("$baseUrl/posts") {
                parameter("_page", page)
                parameter("_limit", limit)
                if (userId != null) parameter("userId", userId)
            }

            when (response.status.value) {
                in 200..299 -> Result.success(response.body())
                else -> Result.failure(
                    HttpException(response.status.value)
                )
            }

        } catch (e: ConnectTimeoutException) {
            Result.failure(TimeoutException())
        } catch (e: SocketTimeoutException) {
            Result.failure(TimeoutException())
        } catch (e: Exception) {
            if (e.message?.contains("Unable to resolve host") == true ||
                e.message?.contains("No address associated") == true
            ) {
                Result.failure(NoConnectionException())
            } else {
                Result.failure(e)
            }
        }
    }
}

class HttpException(val code: Int) : Exception("HTTP $code")
class TimeoutException : Exception("Timeout")
class NoConnectionException : Exception("No connection")