@file:Suppress("unused")

package dev.androidbroadcast.sample.dagger.multibinding.set

import javax.inject.Inject

class Analytics @Inject constructor(
    private val trackers: Set<@JvmSuppressWildcards AnalyticsTracker>
) {

    fun trackLogEvent(event: AnalyticsTracker.Event) {
        trackers.forEach { tracker ->
            tracker.trackEvent(event)
        }
    }
}