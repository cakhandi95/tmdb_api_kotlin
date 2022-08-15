package com.cakhandi95.tmdbapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cakhandi95.tmdbapi.data.model.NetworkedModel.State.*
import com.cakhandi95.tmdbapi.data.model.Popular
import com.cakhandi95.tmdbapi.data.model.TopRated
import com.cakhandi95.tmdbapi.data.model.UpComing
import com.cakhandi95.tmdbapi.databinding.ActivityHomeBinding
import com.cakhandi95.tmdbapi.ui.detail_movies.DetailMoviesActivity
import com.cakhandi95.tmdbapi.ui.popular.PopularMoviesAdapter
import com.cakhandi95.tmdbapi.ui.popular.PopularViewModel
import com.cakhandi95.tmdbapi.ui.top_rated.TopRatedAdapter
import com.cakhandi95.tmdbapi.ui.top_rated.TopRatedViewModel
import com.cakhandi95.tmdbapi.ui.upcoming.UpcomingAdapter
import com.cakhandi95.tmdbapi.ui.upcoming.UpcomingMoviesViewModel
import com.cakhandi95.tmdbapi.utils.ConfigApp
import com.cakhandi95.tmdbapi.utils.OnClickMovies


/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class HomeActivity : AppCompatActivity(), OnClickMovies {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var upcomingMoviesViewModel: UpcomingMoviesViewModel

    private lateinit var adapterPopular: PopularMoviesAdapter
    private lateinit var adapterTopRated: TopRatedAdapter
    private lateinit var adapterUpcoming: UpcomingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topRatedViewModel = ViewModelProvider(this@HomeActivity, ViewModelProvider.AndroidViewModelFactory(application))[TopRatedViewModel::class.java]
        popularViewModel = ViewModelProvider(this@HomeActivity, ViewModelProvider.AndroidViewModelFactory(this.application))[PopularViewModel::class.java]
        upcomingMoviesViewModel = ViewModelProvider(this@HomeActivity, ViewModelProvider.AndroidViewModelFactory(this.application))[UpcomingMoviesViewModel::class.java]

        this.fetchDataMovie(ConfigApp.API_KEY)

        topRatedViewModel.getTopRatedMovies.observe(this) {
            if (it != null) {
               when(it.getState()) {
                   LOADED -> {
                       binding.rvTopRated.visibility = View.VISIBLE
                       binding.pgTopRatedMovies.visibility = View.GONE
                       if (it.getData()?.isNotEmpty() == true){
                          setDataItemsTopRated(it.getData()!!)
                       }
                   }
                   LOADING -> {
                       binding.rvTopRated.visibility = View.GONE
                       binding.pgTopRatedMovies.visibility = View.VISIBLE
                   }
                   ERROR -> {
                       binding.rvTopRated.visibility = View.VISIBLE
                       binding.pgTopRatedMovies.visibility = View.GONE
                       println("error top_rated: ${it.getError().toString()}")
                   }
               }
            }
        }

        popularViewModel.getPopularMovies.observe(this) {
            if (it != null) {
                when(it.getState()) {
                    LOADED -> {
                        println("response popular: ${it.getData().toString()}")
                        binding.rvPopularMovies.visibility = View.VISIBLE
                        binding.pgPopularMovies.visibility = View.GONE
                        setDataItemsPopularMovies(it.getData()!!)
                    }
                    LOADING -> {
                        binding.rvPopularMovies.visibility = View.GONE
                        binding.pgPopularMovies.visibility = View.VISIBLE
                    }
                    ERROR -> {
                        binding.rvPopularMovies.visibility = View.VISIBLE
                        binding.pgPopularMovies.visibility = View.GONE
                        println("error popular: ${it.getError().toString()}")
                    }
                }
            }
        }

        upcomingMoviesViewModel.getUpcomingMovies.observe(this) {
            if (it != null) {
                when(it.getState()) {
                    LOADED -> {
                        binding.rvUpcoming.visibility = View.VISIBLE
                        binding.pgUpcoming.visibility = View.GONE
                        if (it.getData()?.isNotEmpty() == true){
                            setDataItemsUpcomingMovies(it.getData()!!)
                        }
                    }
                    LOADING -> {
                        binding.rvUpcoming.visibility = View.GONE
                        binding.pgUpcoming.visibility = View.VISIBLE
                    }
                    ERROR -> {
                        binding.rvUpcoming.visibility = View.VISIBLE
                        binding.pgUpcoming.visibility = View.GONE
                    }
                }
            }
        }

    }

    private fun setDataItemsTopRated(listTopRated: List<TopRated>) {
        val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        adapterTopRated = TopRatedAdapter(this@HomeActivity, listTopRated)
        adapterTopRated.setOnClickHistoryApproval(this)
        binding.rvTopRated.layoutManager = linearLayoutManager
        binding.rvTopRated.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvTopRated.adapter = adapterTopRated
    }

    private fun setDataItemsPopularMovies(listPopular: List<Popular>) {
        val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        adapterPopular = PopularMoviesAdapter(this@HomeActivity, listPopular)
        adapterPopular.setOnClickHistoryApproval(this)
        binding.rvPopularMovies.layoutManager = linearLayoutManager
        binding.rvPopularMovies.adapter = adapterPopular
    }

    private fun setDataItemsUpcomingMovies(listUpcoming: List<UpComing>) {
        val linearLayoutManager = LinearLayoutManager(this@HomeActivity)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        adapterUpcoming = UpcomingAdapter(this@HomeActivity, listUpcoming)
        adapterUpcoming.setOnClickHistoryApproval(this)
        binding.rvUpcoming.layoutManager = linearLayoutManager
        binding.rvUpcoming.adapter = adapterUpcoming
    }
    
    private fun fetchDataMovie(apiKey: String) {
        topRatedViewModel.get(apiKey)
        popularViewModel.get(apiKey)
        upcomingMoviesViewModel.get(apiKey)
    }

    override fun onClick(movieId: String, movieTitle: String) {
        val intent = Intent(this,DetailMoviesActivity::class.java)
        intent.putExtra(ConfigApp.MOVIE_ID, movieId)
        intent.putExtra(ConfigApp.MOVIE_TITLE,movieTitle)
        startActivity(intent)
    }
}