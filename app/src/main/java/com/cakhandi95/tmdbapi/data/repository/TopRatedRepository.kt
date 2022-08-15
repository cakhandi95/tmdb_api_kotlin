package com.cakhandi95.tmdbapi.data.repository
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cakhandi95.tmdbapi.data.RetrofitService
import com.cakhandi95.tmdbapi.data.api.APIServices
import com.cakhandi95.tmdbapi.data.api.ResponseWrapper
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.TopRated
import com.cakhandi95.tmdbapi.utils.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class TopRatedRepository(application: Application?) {

    private var _topRated: MutableLiveData<NetworkedModel<List<TopRated>>> = MutableLiveData()
    var topRated: LiveData<NetworkedModel<List<TopRated>>> = _topRated

    private var apiServices: APIServices? = null

    init {
        apiServices = RetrofitService.getRetrofitInstance(application)?.create(APIServices::class.java)
    }

    fun loadItemTopRated(apiKey: String) {
        _topRated.value = NetworkedModel()
        apiServices?.getTopRatedMovies(apiKey)?.enqueue(object : Callback<ResponseWrapper<List<TopRated>>>{
            override fun onResponse(call: Call<ResponseWrapper<List<TopRated>>>, response: Response<ResponseWrapper<List<TopRated>>>) {
                ResponseHandler.handleResponse(response,_topRated)
            }

            override fun onFailure(call: Call<ResponseWrapper<List<TopRated>>>, t: Throwable) {
                _topRated.value = NetworkedModel("network")
            }

        })
    }
}
