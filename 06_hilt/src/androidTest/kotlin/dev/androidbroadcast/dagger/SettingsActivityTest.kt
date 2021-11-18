@file:Suppress("unused")

package dev.androidbroadcast.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.androidbroadcast.dagger.data.Analytics
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidTest
class SettingsActivityTest1 {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  // UI tests here.
}

@HiltAndroidTest
class MainActivityTest1 {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @Inject
  lateinit var analytics: Analytics

  @Before
  fun init() {
    hiltRule.inject()
  }

  @Test
  fun mainTest() {
    // Can already use analytics here.
  }
}

class AnalyticsModule {

}

@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [AnalyticsModule::class]
)
interface FakeAnalyticsModule {

  @[Binds Singleton]
  fun bindAnalytics(fakeAnalytics: FakeAnalytics): Analytics
}

@UninstallModules(AnalyticsModule::class)
@HiltAndroidTest
class MainActivityTest2

class FakeAnalytics : Analytics() {

}

@UninstallModules(AnalyticsModule::class)
@HiltAndroidTest
class MainActivityTest {

    @BindValue
    @JvmField
    val analytics: Analytics = FakeAnalytics()

}
