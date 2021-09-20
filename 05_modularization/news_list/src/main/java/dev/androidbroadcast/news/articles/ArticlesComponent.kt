package dev.androidbroadcast.news.articles

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

object ArticlesDepsProvider {

    internal var deps: ArticlesDeps by notNull()

    fun set(deps: ArticlesDeps) {
        this.deps = deps
    }
}

interface ArticlesDeps {

    val newsService: NewsService
}

internal class ArticlesComponentViewModel : ViewModel() {

    init {
        instance = this
    }

    val newDetailsComponent =
        DaggerArticlesComponent.builder()
            .deps(ArticlesDepsProvider.deps)
            .build()

    override fun onCleared() {
        instance = null
    }

    internal companion object {

        private var instance: ArticlesComponentViewModel? = null

        internal val newDetailsComponent: ArticlesComponent
            get() = checkNotNull(instance).newDetailsComponent
    }
}
