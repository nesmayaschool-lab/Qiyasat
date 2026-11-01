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

        // ARCore is optional: the app must still install on devices
        // that do not support Google Play Services for AR.
        manifestPlaceholders["arcoreRequired"] = "optional"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // ARCore support is optional at runtime and will be used only
    // on compatible devices. Camera/measurement features will be
    // added in later project steps.
    implementation("com.google.ar:core:1.50.0")
}
