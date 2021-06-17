@file:Suppress("UNUSED_VARIABLE")

package dev.androidbroadcast.dagger

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

/**
 * Не забудьте указать MainApp в AndroidManifest вашего приложения
 */
class MainApp : Application() {

    /**
     * Это нормальная ситуация что до явного вызова onCreate() системой компонент будет null и до
     * его явной инициализации при попытке поулчения будет выбрасываться исключение
     *
     * **Способ хранения и доступа к компоненту не является единственным и вы можете организовать
     * его по своему**
     */
    private var _appComponent: AppComponent? = null

    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent isn't initialized"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.create()
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