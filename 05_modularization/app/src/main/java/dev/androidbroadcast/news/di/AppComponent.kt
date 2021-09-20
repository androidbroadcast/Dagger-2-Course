package dev.androidbroadcast.news.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.news.articles.ArticlesDeps
import dev.androidbroadcast.news.news_api.NewsService
import javax.inject.Qualifier
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : ArticlesDeps {

    override val newsService: NewsService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@NewsApiQualifier apiKey: String): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideNewsService(@NewsApiQualifier apiKey: String): NewsService {
        return NewsService(apiKey)
    }
}

@Qualifier
annotation class NewsApiQualifier

@Scope
annotation class AppScope
