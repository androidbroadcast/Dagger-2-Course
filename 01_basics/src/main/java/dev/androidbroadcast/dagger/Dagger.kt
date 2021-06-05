package dev.androidbroadcast.dagger

import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {

    fun computer(): Computer
}

@Module
class AppModule {
}
