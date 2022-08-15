package com.cakhandi95.tmdbapi.ui.upcoming

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.data.model.UpComing
import com.cakhandi95.tmdbapi.data.repository.UpComingRepository

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class UpcomingMoviesViewModel(application: Application) : AndroidViewModel(application) {
    private var upComingsoonRepository: UpComingRepository = UpComingRepository(application)
    var getUpcomingMovies: LiveData<NetworkedModel<List<UpComing>>> = upComingsoonRepository.upComing

    fun get(apiKey: String) {
        upComingsoonRepository.loadItemUpComing(apiKey)
    }
}