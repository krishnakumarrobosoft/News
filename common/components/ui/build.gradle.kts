@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.news.ui"
    compileSdk = 34
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompilerVersion.get()
    }
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
    implementation(project(":common:ui:theme"))
    implementation(libs.kotlinKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.jetpackComposeActivity)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.jetpackCoilCompose)
    implementation(libs.jetpackComposeUi)
    implementation(libs.jetpackComposeUiGraphics)
    implementation(libs.jetpackComposeUiTooling)
    implementation(libs.jetpackComposeMaterial)
    implementation(libs.jetpackComposeHiltNavigation)
    testImplementation(libs.junit)
}