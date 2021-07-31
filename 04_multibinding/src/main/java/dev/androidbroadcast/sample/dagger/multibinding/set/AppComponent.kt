package dev.androidbroadcast.sample.dagger.multibinding.set

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import dev.androidbroadcast.sample.dagger.multibinding.utils.AppScope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    val analyticsTrackers: Set<@JvmSuppressWildcards AnalyticsTracker>

    val analytics: Analytics
}

@Module
class AppModule {

    @Provides
    @IntoSet // Добавление зависимости в Set
    fun provideFacebookTracker(): AnalyticsTracker = FacebookAnalyticsTracker()

    @Provides
    @ElementsIntoSet // Возможность добавить несколько элементов в Set
    fun provideFirebaseTracker(
        firebaseAnalyticsTracker: FirebaseAnalyticsTracker,
        appMetricaTracker: AppMetricaTracker
    ): Set<AnalyticsTracker> = setOf(firebaseAnalyticsTracker, appMetricaTracker)
}