@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("android")
}

android {
    namespace = "com.badajoz.badajozentubolsillo.android"
    compileSdk = App.targetSdkVersion
    defaultConfig {
        applicationId = "com.badajoz.badajozentubolsillo.android"
        minSdk = App.minSdkVersion
        targetSdk = App.targetSdkVersion
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue(
            "string",
            "mapbox_access_token",
            System.getenv("MAPBOX_API_KEY") ?: getLocalProperty("MAPBOX_API_KEY")
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    signingConfigs {
        create("release") {
            val tmpFilePath = "keystore/"
            val allFilesFromDir = File(tmpFilePath).listFiles()

            if (allFilesFromDir != null) {
                val keystoreFile = allFilesFromDir.first()
                keystoreFile.renameTo(File("keystore/your_keystore.jks"))
            }

            storeFile = file("keystore/your_keystore.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD") ?: getLocalProperty("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS") ?: getLocalProperty("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD") ?: getLocalProperty("SIGNING_KEY_PASSWORD")
        }
    }
}

dependencies {
    implementation(project(":shared"))

    with(Dependencies.Android) {
        implementation(compose)
        implementation(composeTooling)
        implementation(composePreview)
        implementation(composeIcons)
        implementation(composeFoundation)
        implementation(composeMaterial)
        implementation(composeActivity)
        implementation(coroutinesAndroid)

        implementation(coil)
        implementation(htmlText)
        implementation(mapbox)
        implementation(lottie)
    }

    with(Dependencies.Android.Firebase) {
        implementation(platform(bom))
        implementation(analytics)
        implementation(crashlytics)
        implementation(remoteConfig)
        implementation(fcm)
        implementation(inAppMessaging)
    }

    with(Dependencies.DI) {
        implementation(koinCore)
        implementation(koinAndroid)
        implementation(koinCompose)
    }

    with(Dependencies.Android.Test) {
        testImplementation(junit)
        androidTestImplementation(espresso)

        androidTestImplementation(testRunner)
        androidTestImplementation(testRules)
        androidTestImplementation(androidJunit)
        androidTestImplementation(espressoCore)
        androidTestImplementation(espressoContrib)
        androidTestImplementation(espressoIntents)
        androidTestImplementation(espressoAccesibility)
        androidTestImplementation(espressoWeb)
        androidTestImplementation(espressoConcurrent)
    }
}