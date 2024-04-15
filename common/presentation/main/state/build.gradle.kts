@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    implementation(libs.coroutinesCore)
}