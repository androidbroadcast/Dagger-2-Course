@file:Suppress("unused")

package dev.androidbroadcast.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.androidbroadcast.dagger.data.NewsService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier

@[Module InstallIn(SingletonComponent::class)]
class NetworkModule {

    @Provides
    fun provideNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }
}
