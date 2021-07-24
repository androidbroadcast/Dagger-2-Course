@file:Suppress("unused")

package dev.androidbroadcast.dagger.subcomponent

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope
import javax.inject.Singleton

fun main(appComponent: AppComponent) {
    val featureComponent = appComponent.featureComponent()
        .build()
}

@Module(subcomponents = [FeatureComponent::class])
class AppModule

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun featureComponent(): FeatureComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}


@Scope
annotation class Feature

@Module
class FeatureModule

@Feature
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): FeatureComponent
    }
}
