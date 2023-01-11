plugins {
    id("com.android.application")
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