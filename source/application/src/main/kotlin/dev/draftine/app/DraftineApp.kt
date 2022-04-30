package dev.draftine.app

import android.app.Application

class DraftineApp : Application() {

    override fun onCreate() {
        setTheme(R.style.Theme_Draftine)
        super.onCreate()
    }
}