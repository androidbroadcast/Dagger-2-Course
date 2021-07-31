package dev.androidbroadcast.sample.dagger.multibinding.set


fun main() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    val event: AnalyticsTracker.Event = newLogEvent()
    appComponent.analytics.trackLogEvent(event)
}

fun newLogEvent(): AnalyticsTracker.Event {
    return AnalyticsTracker.Event("log", "none")
}
