// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {


    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")  // Plugin de Kotlin
//        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")  // Plugin de Hilt
    }

}

plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("com.google.dagger.hilt.android") version "2.41" apply false
}
// build.gradle (Project-level)
