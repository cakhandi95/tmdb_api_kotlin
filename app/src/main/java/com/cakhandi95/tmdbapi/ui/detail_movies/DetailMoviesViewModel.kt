package com.cakhandi95.tmdbapi.ui.detail_movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cakhandi95.tmdbapi.data.model.DetailMovies
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.repository.DetailMoviesRepository

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class DetailMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private var detailMoviesRepository: DetailMoviesRepository = DetailMoviesRepository(application)
    var getDetailMovies: LiveData<NetworkedModel<DetailMovies>> = detailMoviesRepository.detailMovies

    fun get(id: String, apiKey: String) {
        detailMoviesRepository.loadDetailMovies(id, apiKey)
    }
}