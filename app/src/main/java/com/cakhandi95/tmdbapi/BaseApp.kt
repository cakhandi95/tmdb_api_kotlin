package com.cakhandi95.tmdbapi
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 ** Created by Handy on 13/08/22
 ** HP ProBook G1 430
 ** handikadwipturadev@gmail.com
 */

class BaseApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}