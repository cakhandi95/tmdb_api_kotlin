package com.cakhandi95.tmdbapi.ui.popular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.Popular
import com.cakhandi95.tmdbapi.data.repository.PopularRepository

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class PopularViewModel(application: Application) : AndroidViewModel(application) {

    private var popularRepository: PopularRepository = PopularRepository(application)
    var getPopularMovies: LiveData<NetworkedModel<List<Popular>>> = popularRepository.popular

    fun get(apiKey: String){
        popularRepository.loadItemPopularMovies(apiKey)
    }

}