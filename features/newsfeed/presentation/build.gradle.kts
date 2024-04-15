@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.news.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompilerVersion.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":business:newsfeed:domain:models"))
    implementation(libs.coroutinesCore)
    implementation(libs.hilt)
    implementation(project(":common:kotlin"))
    implementation(project(":common:navigation:global:presentation:state:main"))
    implementation(project(":common:navigation:global:presentation:models"))
    kapt(libs.hiltCompiler)
    implementation(libs.kotlinKtx)
    implementation(libs.lifeCycleViewModel)
    implementation("androidx.compose.runtime:runtime:1.6.5")
    implementation(project(":business:newsfeed:domain:models"))
    implementation(project(":business:newsfeed:domain:main"))
    implementation(project(":common:presentation:main:state"))
}