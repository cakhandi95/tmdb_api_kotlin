package com.cakhandi95.tmdbapi.ui.detail_movies

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.cakhandi95.tmdbapi.data.model.NetworkedModel
import com.cakhandi95.tmdbapi.databinding.ActivityDetailMoviesBinding
import com.cakhandi95.tmdbapi.utils.ConfigApp

class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviesBinding
    private lateinit var detailMoviesViewModel: DetailMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: ActionBar? = supportActionBar
        val moviesID = intent.getStringExtra(ConfigApp.MOVIE_ID)
        val moviesTitle = intent.getStringExtra(ConfigApp.MOVIE_TITLE)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = moviesTitle
        detailMoviesViewModel = ViewModelProvider(this@DetailMoviesActivity, ViewModelProvider.AndroidViewModelFactory(application))[DetailMoviesViewModel::class.java]

        detailMoviesViewModel.get(moviesID!!, ConfigApp.API_KEY)
        detailMoviesViewModel.getDetailMovies.observe(this) {
            if(it != null) {
                when(it.getState()) {
                    NetworkedModel.State.LOADED -> {
                        val baseUrlImage = "${ConfigApp.BASE_URL_IMAGE}${it.getData()?.posterPath}"
                        val circularProgressDrawable = CircularProgressDrawable(this@DetailMoviesActivity)
                        circularProgressDrawable.strokeWidth = 5f
                        circularProgressDrawable.centerRadius = 30f
                        circularProgressDrawable.start()
                        Glide.with(this@DetailMoviesActivity).load(baseUrlImage).placeholder(circularProgressDrawable).into(binding.imgPoster)

                        binding.tvTitleMovies.text = it.getData()?.title
                        binding.tvRatingValues.text = it.getData()?.voteAverage.toString()
                        binding.tvReleaseDate.text = it.getData()?.releaseDate
                        binding.tvOverview.text = it.getData()?.overview

                    }
                    NetworkedModel.State.LOADING -> {

                    }
                    NetworkedModel.State.ERROR -> {

                    }
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}