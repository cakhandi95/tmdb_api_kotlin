package com.cakhandi95.tmdbapi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cakhandi95.tmdbapi.data.RetrofitService
import com.cakhandi95.tmdbapi.data.api.APIServices
import com.cakhandi95.tmdbapi.data.api.ResponseWrapper
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.Popular
import com.cakhandi95.tmdbapi.utils.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class PopularRepository(application: Application?) {

    private var _popular: MutableLiveData<NetworkedModel<List<Popular>>> = MutableLiveData()
    var popular: LiveData<NetworkedModel<List<Popular>>> = _popular

    private var apiServices: APIServices? = null

    init {
        apiServices = RetrofitService.getRetrofitInstance(application)?.create(
            APIServices::class.java
        )
    }

    fun loadItemPopularMovies(apiKey: String) {
        _popular.value = NetworkedModel()
        apiServices?.getPopularMovies(apiKey)?.enqueue(object : Callback<ResponseWrapper<List<Popular>>> {
            override fun onResponse(call: Call<ResponseWrapper<List<Popular>>>,
                                    response: Response<ResponseWrapper<List<Popular>>>) {
                ResponseHandler.handleResponse(response,_popular)
            }

            override fun onFailure(call: Call<ResponseWrapper<List<Popular>>>, t: Throwable) {
                _popular.value = NetworkedModel("network")
            }

        })
    }

}