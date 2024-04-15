@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    implementation(project(":common:presentation:main:state"))
    implementation(project(":common:navigation:global:presentation:models"))
    implementation(project(":common:presentation:main:state"))
    implementation(project(":common:navigation:api"))
    implementation(project(":business:newsfeed:domain:models"))
    implementation(libs.coroutinesCore)
    implementation(project(":common:kotlin"))
}
