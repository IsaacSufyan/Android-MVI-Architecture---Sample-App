package com.isaacsufyan.mvi

import android.app.Application
import com.isaacsufyan.mvi.di.appModule
import com.isaacsufyan.mvi.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(appModule, storageModule)
        }
    }
}
