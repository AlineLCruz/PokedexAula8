package com.pokedex.data.repository

import com.pokedex.data.model.Post
import com.pokedex.data.remote.*

class PostRepository(private val api: PostApi) {

    suspend fun getPosts(
        page: Int,
        limit: Int,
        userId: Int? = null
    ): Result<List<Post>> {
        return api.getPosts(page, limit, userId).fold(
            onSuccess = { Result.success(it) },
            onFailure = { error ->
                val networkError = when (error) {
                    is HttpException -> NetworkError.HttpError(error.code)
                    is TimeoutException -> NetworkError.Timeout
                    is NoConnectionException -> NetworkError.NoConnection
                    else -> NetworkError.Unknown
                }
                Result.failure(MappedException(networkError))
            }
        )
    }
}

class MappedException(val error: NetworkError) : Exception(error.toMessage())