@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.news.di"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.coroutinesCore)
    implementation(libs.kotlinKtx)
    implementation(project(":common:presentation:main:state"))
    implementation(libs.lifeCycleViewModel)
    implementation(project(":common:kotlin"))
    implementation(project(":common:navigation:global:presentation:state:main"))
    implementation(project(":common:navigation:global:presentation:models"))
    implementation(project(":common:navigation:api"))
    implementation(project(":business:newsfeed:domain:models"))
    kapt(libs.hiltCompiler)
}