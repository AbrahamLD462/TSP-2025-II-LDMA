package com.mexiti.catphotoapp.Network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mexiti.catphotoapp.Model.CatPhoto
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface CatApiService{
    @GET("v1/images/search?limit=10") //realizo la peticion
    suspend fun getPhotos(): List<CatPhoto> //espero el tiempo necesario
}

object CatApi{
    val retrofitService:CatApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }
}