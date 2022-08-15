package com.cakhandi95.tmdbapi.data.api
import com.cakhandi95.tmdbapi.data.model.Popular
import com.cakhandi95.tmdbapi.data.model.TopRated
import com.cakhandi95.tmdbapi.data.model.UpComing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

interface APIServices {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String)  : Call<ResponseWrapper<List<Popular>>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String) : Call<ResponseWrapper<List<TopRated>>>

    @GET("movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String) : Call<ResponseWrapper<List<UpComing>>>

    @GET("movie/{id}")
    fun getDetailMovies(@Path("id") file: String, @Query("api_key") apiKey: String) : Call<ResponseWrapper<List<UpComing>>>

}