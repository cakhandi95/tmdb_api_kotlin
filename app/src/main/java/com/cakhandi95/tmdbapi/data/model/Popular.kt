package com.cakhandi95.tmdbapi.data.model
import com.google.gson.annotations.SerializedName

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

data class Popular (

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String?  = null,

    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("original_language")
    var originalLanguage : String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity : Double?  = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String?  = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    var voteCount: Int?  = null) {

    override fun toString(): String {
        return "Response Popular(" +
                "    adult:$adult" +
                "    backdropPath: $backdropPath" +
                "    genreIDS: $genreIds" +
                "    id: $id" +
                "    originalLanguage: $originalLanguage" +
                "    originalTitle: $originalTitle" +
                "    overview: $overview" +
                "    popularity: $popularity" +
                "    posterPath: $posterPath" +
                "    releaseDate: $releaseDate" +
                "    title: $title" +
                "    video:$video" +
                "    voteAverage: $voteAverage" +
                "    voteCount: $voteCount"
    }
}