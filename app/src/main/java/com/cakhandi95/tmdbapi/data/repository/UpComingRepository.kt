package com.cakhandi95.tmdbapi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cakhandi95.tmdbapi.data.RetrofitService
import com.cakhandi95.tmdbapi.data.api.APIServices
import com.cakhandi95.tmdbapi.data.api.ResponseWrapper
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.UpComing
import com.cakhandi95.tmdbapi.utils.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

open class UpComingRepository(application: Application?) {
    private var _upComing: MutableLiveData<NetworkedModel<List<UpComing>>> = MutableLiveData()
    var upComing: LiveData<NetworkedModel<List<UpComing>>> = _upComing

    private var apiServices: APIServices? = null

    init {
        apiServices = RetrofitService.getRetrofitInstance(application)?.create(
            APIServices::class.java
        )
    }
    fun loadItemUpComing(apiKey: String) {
        _upComing.value = NetworkedModel()
        apiServices?.getUpComingMovies(apiKey)?.enqueue(object : Callback<ResponseWrapper<List<UpComing>>> {
            override fun onResponse(call: Call<ResponseWrapper<List<UpComing>>>, response: Response<ResponseWrapper<List<UpComing>>>) {
                ResponseHandler.handleResponse(response,_upComing)
            }

            override fun onFailure(call: Call<ResponseWrapper<List<UpComing>>>, t: Throwable) {
                _upComing.value = NetworkedModel("network")
            }

        })
    }

}