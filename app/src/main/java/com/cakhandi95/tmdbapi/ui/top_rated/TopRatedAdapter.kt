package com.cakhandi95.tmdbapi.ui.top_rated

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.cakhandi95.tmdbapi.data.model.TopRated
import com.cakhandi95.tmdbapi.databinding.ItemsTopratedBinding
import com.cakhandi95.tmdbapi.utils.ConfigApp
import com.cakhandi95.tmdbapi.utils.OnClickMovies

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class TopRatedAdapter(var appContext: Context, var listTopRated: List<TopRated>) : RecyclerView.Adapter<TopRatedViewHolder>() {

    lateinit var onClickMovies: OnClickMovies

    fun setOnClickHistoryApproval(onClickMovies: OnClickMovies) {
        this.onClickMovies = onClickMovies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TopRatedViewHolder(ItemsTopratedBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val items = listTopRated[position]
        val baseUrlImage = "${ConfigApp.BASE_URL_IMAGE}${items.posterPath}"

        val circularProgressDrawable = CircularProgressDrawable(appContext)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(appContext).load(baseUrlImage).placeholder(circularProgressDrawable).into(holder.binding.imgMovies)

        holder.binding.tvTitleMovies.text = items.title
        holder.binding.root.setOnClickListener {
            onClickMovies.onClick(items.id.toString(),items.title!!)
        }
    }

    override fun getItemCount(): Int = listTopRated.size

}