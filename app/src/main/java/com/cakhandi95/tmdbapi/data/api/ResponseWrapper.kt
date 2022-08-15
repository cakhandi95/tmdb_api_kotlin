package com.cakhandi95.tmdbapi.data.api
import com.google.gson.annotations.SerializedName

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class ResponseWrapper<T>(page: Int, results: T, totalPages: Int, totalResults: Int) {

    @SerializedName("page")
    private var page: Int? = null

    @SerializedName("results")
    private val results: T

    @SerializedName("total_pages")
    private val totalPages: Int

    @SerializedName("total_results")
    private val totalResults: Int

    fun getPage(): Int? {
        return page
    }

    fun getResults(): T {
        return results
    }

    fun getTotalPages(): Int {
        return totalPages
    }

    fun getTotalResults(): Int {
        return totalResults
    }

    init {
        this.page = page
        this.results = results
        this.totalPages = totalPages
        this.totalResults = totalResults
    }
}