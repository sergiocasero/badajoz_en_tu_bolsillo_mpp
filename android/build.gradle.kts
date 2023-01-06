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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
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
        implementation(composeFoundation)
        implementation(composeMaterial)
        implementation(composeActivity)
        implementation(coroutinesAndroid)
    }
}