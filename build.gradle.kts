plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.nesmaya.qiyasat"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.nesmaya.qiyasat"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("com.google.ar:core:1.50.0")
}
