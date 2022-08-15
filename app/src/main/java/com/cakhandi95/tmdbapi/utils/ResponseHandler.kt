package com.cakhandi95.tmdbapi.utils

import androidx.lifecycle.MutableLiveData
import com.cakhandi95.tmdbapi.data.api.ResponseWrapper
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import retrofit2.Response

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

object ResponseHandler {
    fun <T> handleResponse(response: Response<ResponseWrapper<T>>,
        liveData: MutableLiveData<NetworkedModel<T>>) {
        if (response.code() == 200) {
            if (response.body() != null && response.body()!!.getResults() != null) {
                val entry: T = response.body()!!.getResults()
                liveData.setValue(NetworkedModel(entry))
            } else {
                liveData.setValue(NetworkedModel("unknown"))
            }
        } else {
            liveData.setValue(NetworkedModel(ErrorMessage.errorReasonHandler(response)))
        }
    }
}