@file:Suppress("unused")

package dependencies

object Dep {
    object kotlin {
        val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.20"

    }

    object androidx {
        val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        val kotlinExtension = "androidx.core:core-ktx:1.1.0-alpha04"
    }

    object test {
        val junit = "junit:junit:4.12"
    }

    object androidTest {
        object androidx {
            val testRunner = "androidx.test:runner:1.1.0-alpha4"
        }
    }
}