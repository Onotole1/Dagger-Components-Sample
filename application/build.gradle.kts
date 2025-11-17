plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "ru.psbank.modules"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ru.psbank.modules"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dagger.hilt)
    implementation(project(":acquiring:acquiring-feature"))
    implementation(project(":mainscreen:mainscreen-feature"))
    implementation(project(":bookkeeping:bookkeeping-feature"))
    implementation(project(":acquiringoffice:acquiringoffice-feature"))
    implementation(project(":currencyoperations:currencyoperations-feature"))
    ksp(libs.hilt.compiler)
}