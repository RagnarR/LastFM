package com.lastfm.app.network

import android.util.Log
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import javax.inject.Inject

interface NetworkApiCallDelegate {
    @Throws(NetworkApiRepositoryException::class)
    suspend fun <T : Any> executeApiCall(apiCall: suspend () -> T): T
}

internal class NetworkApiCallDelegateImpl @Inject constructor() : NetworkApiCallDelegate {

    @Throws(NetworkApiRepositoryException::class)
    override suspend fun <T : Any> executeApiCall(apiCall: suspend () -> T): T {
        return try {
            apiCall()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            val errorType = getErrorType(throwable)
            throw NetworkApiRepositoryException(errorType)
        }
    }

    private fun getErrorType(throwable: Throwable): Type {
        return when {
            throwable.isConnectionException() -> {
                Type.Connection
            }
            throwable is JsonDataException -> {
                // Log any json data exceptions, as it flags any data that we're not expecting
                // back from the server
                Type.Generic
            }
            throwable is HttpException -> {
                Type.HttpError(throwable.code(), throwable.message())
            }
            else -> {
                Type.Generic
            }
        }
    }
}