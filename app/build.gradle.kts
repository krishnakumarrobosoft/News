plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.news.android"
    compileSdk = 34
    packaging {
        jniLibs {
            excludes.add("META-INF/*")
        }
        resources {
            excludes.add("META-INF/*")
        }
    }

    defaultConfig {
        applicationId = "com.news.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"081dc37dd9f24f52bf1212bd3ffa70d5\"")
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":business:common:app-info"))
    implementation(project(":business:newsfeed:domain:main"))
    implementation(project(":business:newsfeed:domain:models"))
    implementation(project(":business:newsfeed:data:di"))
    implementation(project(":business:network:di"))
    implementation(project(":features:newsfeed:ui"))
    implementation(project(":features:newsfeed:presentation"))
    implementation(project(":features:newsfeed:presentation:di"))
    implementation(project(":common:presentation:main:state"))
    implementation(project(":common:kotlin:di"))
    implementation(project(":common:kotlin"))
    implementation(libs.kotlinKtx)
    implementation(libs.jetpackComposeActivity)
    implementation(libs.lifeCycleRuntimeCompose)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.jetpackComposeUi)
    implementation(libs.jetpackComposeUiGraphics)
    implementation(libs.jetpackComposeUiTooling)
    implementation(project(":common:navigation:global:presentation:state:di"))
    implementation(project(":common:navigation:api"))
    implementation(project(":common:navigation:global:presentation:main"))
    implementation(project(":common:navigation:global:presentation:models"))
    debugImplementation(libs.jetpackComposeDebugTooling)
    implementation(libs.jetpackComposeMaterial)
    implementation(libs.jetpackComposeHiltNavigation)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    testImplementation(libs.junit)
    debugImplementation(libs.jetpackComposeTestDebug)
    implementation(libs.kotlinSerializationJson)
}
kapt {
    correctErrorTypes = true
}