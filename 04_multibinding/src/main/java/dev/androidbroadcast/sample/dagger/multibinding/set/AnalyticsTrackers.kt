package dev.androidbroadcast.sample.dagger.multibinding.set

import javax.inject.Inject

interface AnalyticsTracker {

    fun trackEvent(event: Event) {
        println("${this}: $event")
    }

    data class Event(val name: String, val value: String)
}

class FirebaseAnalyticsTracker @Inject constructor() : AnalyticsTracker {

    override fun toString() = "FirebaseAnalytics"
}

class FacebookAnalyticsTracker @Inject constructor() : AnalyticsTracker {

    override fun toString() = "FacebookAnalytics"
}

class AppMetricaTracker @Inject constructor() : AnalyticsTracker {

    override fun toString() = "AppMetrica"
}