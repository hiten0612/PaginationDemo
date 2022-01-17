package com.example.paginationdemo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("character")
    fun getDataFromApi(@Query("page") page: Int): Call<RickAndMortyList>
}