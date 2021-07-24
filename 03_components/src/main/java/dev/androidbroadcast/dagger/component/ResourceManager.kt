package dev.androidbroadcast.dagger.component

import android.content.Context
import javax.inject.Inject

@AppScope
class ResourceManager @Inject constructor(private val context: Context)