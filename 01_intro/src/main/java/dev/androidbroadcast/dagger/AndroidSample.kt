@file:Suppress("UNUSED_VARIABLE", "unused")

package dev.androidbroadcast.dagger

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

/**
 * Не забудьте указать MainApp в AndroidManifest вашего приложения
 *
 * Алтернативы инициализации смотрите в [MainApp2]
 *
 * @see MainApp2
 */
class MainApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}


/**
 * Специальное расширение для получения AppComponent в любом месте, где у вас есть доступ к Context.
 * Позволяет избегать статического хранения ссылки на ваш [Application] класс
 */
val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val computer = appComponent.computer()
    }
}