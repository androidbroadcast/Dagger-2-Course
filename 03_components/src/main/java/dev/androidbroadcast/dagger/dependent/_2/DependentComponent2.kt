@file:Suppress("unused")

package dev.androidbroadcast.dagger.dependent._2

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent : FeatureDeps {

    override fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}

// ----------------------------------------------------------

interface FeatureDeps {

    fun application(): Application

}

@Component(dependencies = [FeatureDeps::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: FeatureDeps): Builder

        fun build(): FeatureComponent
    }
}

