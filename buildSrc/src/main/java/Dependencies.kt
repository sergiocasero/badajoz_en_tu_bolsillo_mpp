const val propertiesDir = "signin/properties"

const val coroutines_version = "1.6.4"
const val kotlin_version = "1.7.10"
const val android_plugin_version = "8.0.0-alpha09"
const val ktor_version = "2.2.2"
const val sqldelight_version = ""
const val espressoVersion = "3.5.1"
const val junitVersion = "4.12"
const val serialization_version = "1.4.1"

object App {
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}

object Common {
    const val minSdkVersion = App.minSdkVersion
    const val targetSdkVersion = App.targetSdkVersion
    const val testInstrumentationRunner = App.testInstrumentationRunner
}

object Dependencies {
    object Android {
        // Android
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
        const val recycler = "androidx.recyclerview:recyclerview:1.2.0"
        const val material = "com.google.android.material:material:1.4.0"

        const val compose = "androidx.compose.ui:ui:1.3.2"
        const val composeTooling = "androidx.compose.ui:ui-tooling:1.3.2"
        const val composePreview = "androidx.compose.ui:ui-tooling-preview:1.3.2"
        const val composeFoundation = "androidx.compose.foundation:foundation:1.2.1"
        const val composeMaterial = "androidx.compose.material:material:1.3.1"
        const val composeActivity = "androidx.activity:activity-compose:1.3.1"

        // Coroutines
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

        object Test {
            // Adds a remote binary dependency only for local tests.
            const val junit = "junit:junit:4.12"

            // Adds a remote binary dependency only for the instrumented test APK.
            const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"

            // AndroidJUnitRunner and JUnit Rules
            const val testRunner = "androidx.test:runner:1.5.2"
            const val testRules = "androidx.test:rules:1.5.0"

            // Assertions
            const val androidJunit = "androidx.test.ext:junit:1.1.5"

            // Espresso dependencies
            const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
            const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
            const val espressoIntents = "androidx.test.espresso:espresso-intents:$espressoVersion"
            const val espressoAccesibility = "androidx.test.espresso:espresso-accessibility:$espressoVersion"
            const val espressoWeb = "androidx.test.espresso:espresso-web:$espressoVersion"
            const val espressoConcurrent = "androidx.test.espresso.idling:idling-concurrent:$espressoVersion"

            // The following Espresso dependency can be either "implementation",
            // or "androidTestImplementation", depending on whether you want the
            // dependency to appear on your APK’"s compile classpath or the test APK
            // classpath.
            const val espressoResource = "androidx.test.espresso:espresso-idling-resource:$espressoVersion"


        }
    }

    object Root {
        const val android = "com.android.tools.build:gradle:7.0.3"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }

    object Common {
        object Main {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
            const val serialization =
                "org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version"

            const val ktorClientCore = "io.ktor:ktor-client-core:$ktor_version"
            const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktor_version"
            const val ktorClientJson = "io.ktor:ktor-client-json:$ktor_version"
            const val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:$ktor_version"
            const val ktorClientAuth = "io.ktor:ktor-client-auth:$ktor_version"
            const val ktorLogging = "io.ktor:ktor-client-logging:$ktor_version"
        }

        object Android {
            const val ktorClientCore = "io.ktor:ktor-client-okhttp:$ktor_version"
            const val sqldelightDriverAndroid =
                "com.squareup.sqldelight:android-driver:$sqldelight_version"
        }

        object Native {
            const val ktorClientCore = "io.ktor:ktor-client-ios:$ktor_version"
            const val sqldelightDriverNative =
                "com.squareup.sqldelight:native-driver:$sqldelight_version"
        }
    }
}
