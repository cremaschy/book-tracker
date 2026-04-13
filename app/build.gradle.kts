import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "br.com.fatec.book.tracker"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "br.com.fatec.book.tracker"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.compose.material.icons.extended)

    // Datastore
    implementation(libs.androidx.datastore.preferences)

    // Navigation 3
    implementation(libs.bundles.navigation3)
    implementation(libs.serialization.core)
    implementation(libs.serialization.json)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // Ktor
    implementation(libs.bundles.ktor)

    // Coil (Image Loading)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor)
    implementation(libs.androidx.compose.foundation.layout)

    // Testing
    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.testing)

    debugImplementation(libs.androidx.compose.ui.tooling)
}