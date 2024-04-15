@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
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
    implementation(project(":common:components:ui"))
    implementation(project(":features:newsfeed:presentation"))
    implementation(project(":common:ui:theme"))
    implementation(project(":business:newsfeed:domain:models"))
    implementation(project(":common:presentation:main:state"))
    implementation(libs.kotlinKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    implementation(libs.jetpackComposeActivity)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.jetpackComposeUi)
    implementation(libs.jetpackComposeUiGraphics)
    implementation(libs.jetpackComposeUiTooling)
    implementation(libs.jetpackComposeMaterial)
    implementation(libs.jetpackComposeHiltNavigation)
    debugImplementation(libs.androidx.ui.tooling)
}