package com.cakhandi95.tmdbapi.data.api

import com.cakhandi95.tmdbapi.data.model.DetailMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

interface DetailMoviesAPI {
    @GET("movie/{id}")
    fun getDetailMovies(@Path("id") id: String, @Query("api_key") apiKey: String) : Call<DetailMovies>
}