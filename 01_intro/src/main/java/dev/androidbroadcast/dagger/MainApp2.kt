package dev.androidbroadcast.dagger

import android.app.Application

/**
 * Не забудьте указать MainApp в AndroidManifest вашего приложения
 */
class MainApp2 : Application() {

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