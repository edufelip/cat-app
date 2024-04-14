package com.edufelip.catapp.data.service

import com.edufelip.catapp.data.model.CatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {

    @GET("cat/{id}")
    fun getCatDetail(@Path("id") id: String): Response<CatResponse>
}