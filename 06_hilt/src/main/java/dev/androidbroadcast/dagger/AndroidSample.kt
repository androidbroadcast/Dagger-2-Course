@file:Suppress("UNUSED_VARIABLE", "unused", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate")

package dev.androidbroadcast.dagger

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.androidbroadcast.dagger.data.Analytics
import dev.androidbroadcast.dagger.data.News
import dev.androidbroadcast.dagger.data.NewsRepository
import dev.androidbroadcast.dagger.databinding.FragmentNewsDetailsBinding
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        with(supportFragmentManager) {
            if (isFragmentContainerEmpty(R.id.fragments)) {
                commit {
                    add<NewsDetailsFragment>(
                        R.id.fragments,
                        FRAGMENT_NEWS_DETAILS,
                        NewsDetailsFragment.makeArgs(TOP_NEWS_ID)
                    )
                }
            }
        }
    }

    @Inject
    fun trackOnStart(analytics: Analytics) {
        analytics.trackScreenShow()
    }

    private companion object {

        private const val TOP_NEWS_ID = "top"
        private const val FRAGMENT_NEWS_DETAILS = "newsDetails"
    }
}

class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private val viewBinding by viewBinding(FragmentNewsDetailsBinding::bind)
    private val newsId: String by stringArgs(ARG_NEWS_ID)
    private val viewModel: NewsDetailsViewModel by viewModels {
        factory.create(newsId)
    }

    // Lazy и Provider не работаю с зависимостями, которые используют Assisted Inject
    @Inject
    lateinit var factory: NewsDetailsViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collect(::updateUi)
            }
        }
    }

    private fun updateUi(news: News) {
        if (view == null) return
        with(viewBinding) {
            title.text = news.title
            body.text = news.body
        }
    }

    companion object {

        private const val ARG_NEWS_ID = "news_id"

        fun makeArgs(newsId: String): Bundle {
            return Bundle(1).apply {
                putString(ARG_NEWS_ID, newsId)
            }
        }
    }
}

class NewsDetailsViewModel(
    private val newsId: String,
    private val newsRepository: NewsRepository
) : ViewModel() {

    val news: SharedFlow<News> =
        flow<News> { newsRepository.getNews(newsId) }
            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
}

class NewsDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("newsId") private val newsId: String,
    private val newsRepository: NewsRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == NewsDetailsViewModel::class)
        return NewsDetailsViewModel(newsId, newsRepository) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("newsId") newsId: String): NewsDetailsViewModelFactory
    }
}
