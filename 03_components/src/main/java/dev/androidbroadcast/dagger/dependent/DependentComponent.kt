package dev.androidbroadcast.dagger.dependent

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

    fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}

// ----------------------------------------------------------

@Component(dependencies = [AppComponent::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): FeatureComponent
    }
}

