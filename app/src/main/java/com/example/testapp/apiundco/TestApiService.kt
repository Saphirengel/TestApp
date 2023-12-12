package com.example.testapp.apiundco

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

const val BASE_URL ="https://retoolapi.dev/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TestApiService{

    @GET("ZUeZzn/unterassen")
    suspend fun getUnterrasse(): List<Unterrasse>
}

object RassenApi{
    val retrofitService: TestApiService by lazy { retrofit.create(TestApiService::class.java) }
}