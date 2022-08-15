package com.cakhandi95.tmdbapi.data.model

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class NetworkedModel<T> {

    enum class State {
        LOADED, LOADING, ERROR
    }

    private var data: T? = null
    private var errorMessage: String? = null
    private var state: State

    constructor() {
        state = State.LOADING
    }

    constructor(data: T) {
        this.data = data
        errorMessage = null
        state = State.LOADED
    }

    constructor(errorMessage: String?) {
        this.errorMessage = errorMessage
        state = State.ERROR
    }

    constructor(exception: Exception) {
        errorMessage = exception.message
        state = State.ERROR
    }

    fun getData(): T? {
        return data
    }

    fun getError(): String? {
        return errorMessage
    }

    fun getState(): State {
        return state
    }

    fun isError(): Boolean {
        return errorMessage != null
    }
}