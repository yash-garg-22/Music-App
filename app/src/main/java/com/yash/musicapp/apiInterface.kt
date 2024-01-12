package com.yash.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface apiInterface {

    @Headers("X-RapidAPI-Key: 2a613780e1mshf0d441dde669249p16553ajsn53532c2b133c",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<AllData>

}