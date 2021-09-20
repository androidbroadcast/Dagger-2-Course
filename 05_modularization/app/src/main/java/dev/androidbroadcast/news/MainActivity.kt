package dev.androidbroadcast.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.androidbroadcast.news.articles.ArticlesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentManager.commit {
                add<ArticlesFragment>(R.id.fragment_container, FRAGMENT_ARTICLES)
            }
        }
    }

    private companion object {

        private const val FRAGMENT_ARTICLES = "articles"
    }
}
