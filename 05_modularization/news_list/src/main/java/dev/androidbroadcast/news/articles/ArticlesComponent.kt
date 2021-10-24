@file:Suppress("unused")

package dev.androidbroadcast.news.articles

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import dagger.Component
import dev.androidbroadcast.news.core.Feature
import dev.androidbroadcast.news.news_api.NewsService
import kotlin.properties.Delegates.notNull

@[Feature Component(dependencies = [ArticlesDeps::class])]
internal interface ArticlesComponent {

    fun inject(fragment: ArticlesFragment)

    @Component.Builder
    interface Builder {

        fun deps(articlesDeps: ArticlesDeps): Builder

        fun build(): ArticlesComponent
    }
}

interface ArticlesDeps {

    val newsService: NewsService
}

interface ArticlesDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: ArticlesDeps

    companion object : ArticlesDepsProvider by ArticlesDepsStore
}

object ArticlesDepsStore : ArticlesDepsProvider {

    override var deps: ArticlesDeps by notNull()
}

internal class ArticlesComponentViewModel : ViewModel() {

    val newDetailsComponent =
        DaggerArticlesComponent.builder().deps(ArticlesDepsProvider.deps).build()
}
