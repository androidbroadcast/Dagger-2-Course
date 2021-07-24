package dev.androidbroadcast.dagger.component

import dagger.Reusable
import javax.inject.Inject

@Reusable
class Counter @Inject constructor() {

    var count = 0
}