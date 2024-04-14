package com.edufelip.catapp.data.di

import android.util.Log
import com.edufelip.catapp.data.service.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCatService(retrofit: Retrofit): CatApi =
        retrofit.create(CatApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val baseUrl = "https://www.cataas.com/"
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(createHttpLoggingInterceptor())
        }
        .build()

    private fun createHttpLoggingInterceptor() =
        HttpLoggingInterceptor { message ->
            Log.d(OKHTTP_TAG, message)
        }

    companion object {
        private const val CONNECT_TIMEOUT_IN_SECONDS = 10L
        private const val WRITE_TIMEOUT_IN_SECONDS = 1L
        private const val READ_TIMEOUT_IN_SECONDS = 20L
        private const val OKHTTP_TAG = "OkHttp"
    }
}