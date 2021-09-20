@file:Suppress("MemberVisibilityCanBePrivate")

package dev.androidbroadcast.news

import android.app.Application
import dev.androidbroadcast.news.articles.ArticlesDeps
import dev.androidbroadcast.news.articles.ArticlesDepsProvider
import dev.androidbroadcast.news.di.AppComponent
import dev.androidbroadcast.news.di.DaggerAppComponent

class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.NEWS_API_KEY)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        ArticlesDepsProvider.set(appComponent)
    }
}
