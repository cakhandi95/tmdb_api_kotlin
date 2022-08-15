package com.cakhandi95.tmdbapi.ui.top_rated

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.TopRated
import com.cakhandi95.tmdbapi.data.repository.TopRatedRepository

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class TopRatedViewModel(application: Application) : AndroidViewModel(application) {
    private var topRatedRepository: TopRatedRepository = TopRatedRepository(application)
    var getTopRatedMovies: LiveData<NetworkedModel<List<TopRated>>> = topRatedRepository.topRated

    fun get(apiKey: String) {
        topRatedRepository.loadItemTopRated(apiKey)
    }
}