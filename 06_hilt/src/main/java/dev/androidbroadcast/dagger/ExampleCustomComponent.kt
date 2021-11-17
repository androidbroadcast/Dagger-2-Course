package dev.androidbroadcast.dagger

import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent


@DefineComponent(parent = SingletonComponent::class)
internal interface ExampleCustomComponent {

    @DefineComponent.Builder
    interface Builder {

        fun fooSeedData(@BindsInstance name:String): Builder?

        fun build(): ExampleCustomComponent
    }
}