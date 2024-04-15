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
    implementation(project(":common:kotlin:di"))
    implementation(project(":common:presentation:main:state"))
    implementation(project(":features:newsfeed:presentation"))
    implementation(libs.kotlinKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    kapt(libs.hiltCompiler)
    implementation(project(":common:kotlin"))
    implementation(project(":common:kotlin:di"))
    testImplementation(libs.junit)
}