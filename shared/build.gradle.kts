plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios()
    iosSimulatorArm64()

    cocoapods {
        framework {
            baseName = project.name
            summary = "KMM permissions UI"
            homepage = "http"
            isStatic = true
        }
        ios.deploymentTarget = "11"
        version = "1.11.3"
    }

    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(projects.permissions)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation("io.insert-koin:koin-core:3.4.0")
            }
        }
        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.adrianwitaszak.kmmpermissions"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
