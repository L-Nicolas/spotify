package fr.upsilon.spotify.model.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://theaudiodb.com/api/v1/json/523532")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://theaudiodb.com/api/v1/json/523532")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    val api: ArtistService by lazy {
        retrofit.create(ArtistService::class.java)
    }
}