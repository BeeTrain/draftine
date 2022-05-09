package dev.draftine.app

import android.app.Application
import dev.draftine.annotation.processing.koin.annotation.KoinInitializer
import dev.draftine.app.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@KoinInitializer
class DraftineApp : Application() {

    override fun onCreate() {
        setTheme(R.style.Theme_Draftine)
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DraftineApp)
            modules(koinModules)
        }
    }
}