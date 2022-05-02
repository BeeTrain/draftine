package dev.draftine.app

import android.app.Application
import dev.draftine.app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DraftineApp : Application() {

    override fun onCreate() {
        setTheme(R.style.Theme_Draftine)
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DraftineApp)
            modules(AppModule.modules)
        }
    }
}