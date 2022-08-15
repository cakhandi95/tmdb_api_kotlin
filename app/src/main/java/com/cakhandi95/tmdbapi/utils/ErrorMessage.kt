package com.cakhandi95.tmdbapi.utils

import com.cakhandi95.tmdbapi.data.api.ErrorAPI
import com.google.gson.GsonBuilder
import retrofit2.Response
import java.io.IOException

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

object ErrorMessage {

    @Throws(IOException::class)
    fun decodeErrorJson(response: Response<*>): ErrorAPI? {
        val body = response.errorBody()!!.string()
        val gson = GsonBuilder().serializeNulls().create()
        return gson.fromJson(body, ErrorAPI::class.java)
    }

    fun errorReasonHandler(response: Response<*>): String? {
        return try {
            val error: ErrorAPI? = decodeErrorJson(response)
            error?.statusMessage
        } catch (e: Exception) {
            "unknown"
        }
    }

}