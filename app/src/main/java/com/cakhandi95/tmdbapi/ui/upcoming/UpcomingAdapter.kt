package com.cakhandi95.tmdbapi.ui.upcoming

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.cakhandi95.tmdbapi.data.model.UpComing
import com.cakhandi95.tmdbapi.databinding.ItemsUpcomingBinding
import com.cakhandi95.tmdbapi.utils.ConfigApp
import com.cakhandi95.tmdbapi.utils.OnClickMovies

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class UpcomingAdapter(var appContext: Context, var listUpcoming: List<UpComing>) : RecyclerView.Adapter<UpcomingViewHolder>() {

    lateinit var onClickMovies: OnClickMovies

    fun setOnClickHistoryApproval(onClickMovies: OnClickMovies) {
        this.onClickMovies = onClickMovies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UpcomingViewHolder(ItemsUpcomingBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val items = listUpcoming[position]
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

    override fun getItemCount(): Int = listUpcoming.size

}