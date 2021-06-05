plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val daggerVersion = "2.36"

dependencies {
    implementation("com.google.dagger:dagger:$daggerVersion")

    // Kotlin
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    // Java
    // annotationProcessor "com.google.dagger:dagger-compiler:$daggerVersion"
}