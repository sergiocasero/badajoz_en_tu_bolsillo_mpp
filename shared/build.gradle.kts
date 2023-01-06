plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version kotlin_version
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
                with(Dependencies.Common.Main) {
                    implementation(coroutines)
                    implementation(serialization)
                    implementation(ktorClientCore)
                    implementation(ktorClientJson)
                    implementation(ktorSerialization)
                    implementation(ktorClientAuth)
                    implementation(ktorLogging)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
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
    compileSdk = 33
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
}