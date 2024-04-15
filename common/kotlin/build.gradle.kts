@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kotlin")
    alias(libs.plugins.kotlinSerialization)
}


dependencies {
    implementation(libs.coroutinesCore)
    implementation(libs.inject)
}