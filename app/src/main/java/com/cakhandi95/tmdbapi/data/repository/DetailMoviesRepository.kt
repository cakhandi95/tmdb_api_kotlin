package com.cakhandi95.tmdbapi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cakhandi95.tmdbapi.data.RetrofitService
import com.cakhandi95.tmdbapi.data.api.DetailMoviesAPI
import com.cakhandi95.tmdbapi.data.api.ErrorAPI
import com.cakhandi95.tmdbapi.data.model.DetailMovies
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.utils.ErrorMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class DetailMoviesRepository(application: Application?) {

    private var _detailMovies: MutableLiveData<NetworkedModel<DetailMovies>> = MutableLiveData()
    var detailMovies: LiveData<NetworkedModel<DetailMovies>> = _detailMovies

    private var apiServices: DetailMoviesAPI? = null

    init {
        apiServices = RetrofitService.getRetrofitInstance(application)?.create(DetailMoviesAPI::class.java)
    }

    fun loadDetailMovies(id: String, apiKey: String) {
        _detailMovies.value = NetworkedModel()
        apiServices?.getDetailMovies(id, apiKey)?.enqueue(object : Callback<DetailMovies> {

            override fun onResponse(call: Call<DetailMovies>, response: Response<DetailMovies>) {
                if (response.code() == 200) {
                    _detailMovies.value = NetworkedModel(response.body()!!)
                } else {
                    val error: ErrorAPI? = ErrorMessage.decodeErrorJson(response)
                    _detailMovies.value = NetworkedModel(error?.statusMessage)
                }
            }

            override fun onFailure(call: Call<DetailMovies>, t: Throwable) {
                _detailMovies.value = NetworkedModel("network")
            }

        })
    }

}