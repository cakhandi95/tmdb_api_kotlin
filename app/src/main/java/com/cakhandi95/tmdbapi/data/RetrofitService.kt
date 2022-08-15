package com.cakhandi95.tmdbapi.data

import android.content.Context
import com.cakhandi95.tmdbapi.utils.ConfigApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

object RetrofitService {

    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(context: Context?): Retrofit? {
        if (retrofit == null) {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(ConfigApp.readTimeout,TimeUnit.SECONDS)
            builder.writeTimeout(ConfigApp.writeTimeout,TimeUnit.SECONDS)
            builder.connectTimeout(ConfigApp.connectTimeout,TimeUnit.SECONDS)
            val client = builder.build()
            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(ConfigApp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}