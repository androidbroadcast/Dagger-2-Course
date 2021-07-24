package dev.androidbroadcast.dagger

import android.app.Application
import android.content.Context
import dev.androidbroadcast.dagger.subcomponent.AppComponent
import dev.androidbroadcast.dagger.subcomponent.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }