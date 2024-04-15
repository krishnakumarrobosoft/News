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
    implementation(project(":business:common:app-info"))
    implementation(libs.kotlinKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    implementation(libs.kotlinSerializationJson)
    implementation(libs.kotlinSerializationConverter)
    implementation(libs.retrofit)
    implementation(libs.retrofitGsonConverter)
    implementation(libs.kotlinSerializationConverter)
    implementation(libs.okHttp)
    implementation(libs.loggingInterceptor)
    implementation(project(":business:network:main"))
    testImplementation(libs.junit)
}