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
        }

        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        /*maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                val MAPBOX_DOWNLOADS_TOKEN: String by project
                password = MAPBOX_DOWNLOADS_TOKEN
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }*/
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}