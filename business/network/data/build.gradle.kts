plugins {
    id("kotlin")
    alias(libs.plugins.kotlinSerialization)
}


dependencies {
    implementation(libs.kotlinSerializationConverter)
}