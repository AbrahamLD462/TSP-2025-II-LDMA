package com.example.duckapp.network

import com.example.duckapp.model.DuckPhoto
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.create
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient

private const val BASE_URL = "https://random-d.uk/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient())
    .build()

interface DuckApiService {
    @GET("api/random")
    suspend fun getRandomDuck(): DuckPhoto
}

object DuckApi {
    val retrofitService: DuckApiService by lazy {
        retrofit.create(DuckApiService::class.java)
    }
}

