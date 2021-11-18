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
import androidx.lifecycle.*
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
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

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

@AndroidEntryPoint
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private val viewBinding by viewBinding(FragmentNewsDetailsBinding::bind)
    private val newsId: String by stringArgs(ARG_NEWS_ID)
    private val viewModel: NewsDetailsViewModel by lazy {
        val viewModel: NewsDetailsViewModel by viewModels()
        viewModel.newsId = newsId
        viewModel
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

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle // По умолчанию существую в Scope ViewModel-и
) : ViewModel() {

    lateinit var newsId: String

    val news: SharedFlow<News> =
        flow<News> { newsRepository.getNews(newsId) }
            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
}
