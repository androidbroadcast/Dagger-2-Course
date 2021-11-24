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

    buildFeatures {
        viewBinding = true
    }
}

val daggerVersion = "2.36"
val archComponents = "2.4.0-alpha02"

dependencies {
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("androidx.fragment:fragment-ktx:1.4.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$archComponents")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$archComponents")
    implementation("androidx.lifecycle:lifecycle-common-java8:$archComponents")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$archComponents")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.4.6")
}