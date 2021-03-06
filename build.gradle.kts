import java.io.FileInputStream
import java.util.*

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlinVersion by extra { "1.3.20" }
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-beta02")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.3.2.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}


task(
    "clean",
    Delete::class
) {
    delete = setOf(
        rootProject.buildDir
    )
}

task(
    "assemble",
    Assemble::class
) {

}

task(
    "testClasses"
) {

}