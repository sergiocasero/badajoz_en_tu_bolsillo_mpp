const val propertiesDir = "signin/properties"

const val coroutines_version = "1.6.4"
const val kotlin_version = "1.7.10"
const val android_plugin_version = "8.0.0-alpha09"
const val ktor_version = "2.2.2"
const val sqldelight_version = ""

const val serialization_version = "1.4.1"

object App {
    const val minSdkVersion = 23
    const val targetSdkVersion = 31
    const val versionCode = 20220117
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

        // Coroutines
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
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
            const val ktorClientJson = "io.ktor:ktor-client-json:$ktor_version"
            const val ktorSerialization = "io.ktor:ktor-client-serialization:$ktor_version"
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
