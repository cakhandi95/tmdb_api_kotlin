package com.cakhandi95.tmdbapi.utils

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

object ConfigApp {

    // TODO URL TMDB API
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "2174d146bb9c0eab47529b2e77d6b526"
    const val MOVIE_ID = "id_movies"
    const val MOVIE_TITLE = "title_movies"

    // TODO API CLIENT OKHTTP >=  v.3.17.2
    const val connectTimeout: Long = 30
    const val readTimeout: Long = 10
    const val writeTimeout: Long = 10
}