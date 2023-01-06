plugins {
    id("com.android.application").version(android_plugin_version).apply(false)
    id("com.android.library").version(android_plugin_version).apply(false)
    kotlin("android").version(kotlin_version).apply(false)
    kotlin("multiplatform").version(kotlin_version).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
