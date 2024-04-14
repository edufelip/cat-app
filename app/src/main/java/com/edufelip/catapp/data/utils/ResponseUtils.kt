package com.edufelip.catapp.data.utils

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> safeApiCall(
    call: () -> Response<T>
): T =
    try {
        val response = call()
        if (!response.isSuccessful) {
            response.body() ?: throw HttpException(response)
        } else {
            throw HttpException(response)
        }
    } catch (e: Exception) {
        throw e
    }
