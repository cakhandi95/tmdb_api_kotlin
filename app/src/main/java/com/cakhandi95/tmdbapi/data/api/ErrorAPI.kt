package com.cakhandi95.tmdbapi.data.api

import com.google.gson.annotations.SerializedName

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

data class ErrorAPI (
    @SerializedName("status_code")
    var statusCode: Int? = null,

    @SerializedName("status_message")
    var statusMessage: String? = null,

    @SerializedName("success")
    var success: Boolean? = null
)