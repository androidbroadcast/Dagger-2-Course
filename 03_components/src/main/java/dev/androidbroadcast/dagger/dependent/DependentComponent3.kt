package dev.androidbroadcast.dagger.dependent3

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@Component
interface AppComponent {

    fun application(): Application

    fun featureDeps(): FeatureDeps

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}

// ----------------------------------------------------------

class FeatureDeps @Inject constructor(
    private val application: Application
) {

    fun application() = application
}

@Component(dependencies = [FeatureDeps::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: FeatureDeps): Builder

        fun build(): FeatureComponent
    }
}

