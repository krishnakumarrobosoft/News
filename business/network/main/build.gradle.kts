@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id( "kotlin")
    id("kotlin-kapt")
}


dependencies {
    implementation(libs.retrofit)
    implementation(libs.inject)
    implementation(project(":business:common:app-info"))
    implementation(project(":business:network:data"))
}