package dev.androidbroadcast.dagger

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    val computer = appComponent.computer()
    print(computer)
}