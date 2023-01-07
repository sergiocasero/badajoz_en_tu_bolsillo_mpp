plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Dependencies.Shared.Main) {
                    implementation(coroutines)
                    implementation(serialization)
                    implementation(ktorClientCore)
                    implementation(ktorClientJson)
                    implementation(ktorContentNegotiation)
                    implementation(ktorSerialization)
                    implementation(ktorSerializationJson)
                    implementation(ktorClientAuth)
                    implementation(ktorLogging)
                }

                with(Dependencies.DI) {
                    implementation(koinCore)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies.Shared.Android) {
                    implementation(ktorClientCore)
                    api(lifecycleViewModel)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.badajoz.badajozentubolsillo"
    compileSdk = App.targetSdkVersion
    defaultConfig {
        minSdk = App.minSdkVersion
        targetSdk = App.targetSdkVersion
    }
}