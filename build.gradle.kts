buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        with(Dependencies.Root) {
            classpath(android)
            classpath(serialization)
            classpath(google)
            classpath(crashlytics)
        }

        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                val token: String = if (System.getenv("MAPBOX_DOWNLOADS_TOKEN") != null) {
                    System.getenv("MAPBOX_DOWNLOADS_TOKEN")
                } else {
                    project.properties["MAPBOX_DOWNLOADS_TOKEN"] as String
                }
                password = token
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}