@file:Suppress("unused")

package dev.androidbroadcast.dagger.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Qualifier
import javax.inject.Scope

class DaggerApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .appId("dev.androidbroadcast.dagger.components")
            .mainHost("https://androidbroadcast.dev/dagger/api")
            .build()
    }
}

@Component
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun appId(@AppId appId: String): Builder

        @BindsInstance
        fun mainHost(@MainHost host: String): Builder

        fun build(): AppComponent
    }
}

@Qualifier
annotation class AppId

@Qualifier
annotation class MainHost

@Scope
annotation class AppScope







