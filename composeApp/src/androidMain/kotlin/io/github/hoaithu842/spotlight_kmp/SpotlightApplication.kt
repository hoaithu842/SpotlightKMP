package io.github.hoaithu842.spotlight_kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext

class SpotlightApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(
            appComponent = AndroidApplicationComponent()
        ) {
            androidContext(this@SpotlightApplication)
        }
    }
}