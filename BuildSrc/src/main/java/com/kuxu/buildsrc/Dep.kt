@file:Suppress("unused")

package dependencies

import dependencies.Versions.coroutineVersion
import dependencies.Versions.kotlinSerializerVersion
import dependencies.Versions.kotlinVersion
import dependencies.Versions.kvsSchemaVersion
import dependencies.Versions.navigationVersion

object Dep {
    object kotlin {
        val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${kotlinVersion}"
        val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutineVersion}"
        val serializer = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${kotlinSerializerVersion}"
    }

    val material = "com.google.android.material:material:1.0.0"

    object androidx {
        val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        val kotlinExtension = "androidx.core:core-ktx:1.1.0-alpha04"
        val appCompat = "androidx.appcompat:appcompat:1.0.0-beta01"

        val navigationFragment = "android.arch.navigation:navigation-fragment:${navigationVersion}"
        val navigationUi = "android.arch.navigation:navigation-ui:${navigationVersion}"
        val navigationRuntimeKtx = "android.arch.navigation:navigation-runtime-ktx:${navigationVersion}"
        val navigatiomCommonKtx = "android.arch.navigation:navigation-common-ktx:${navigationVersion}"
        val navigationFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${navigationVersion}"
        val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${navigationVersion}"
    }

    val kvsSchema = "com.rejasupotaro:kvs-schema:${kvsSchemaVersion}"
    val kvsSchemaCompiler = "com.rejasupotaro:kvs-schema-compiler:${kvsSchemaVersion}"

    object test {
        val junit = "junit:junit:4.12"
    }

    object androidTest {
        object androidx {
            val testRunner = "androidx.test:runner:1.1.0-alpha4"
            val espresso = "androidx.test.espresso:espresso-core:3.1.0-alpha4"
        }
    }
}