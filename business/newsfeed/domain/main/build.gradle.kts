@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    implementation(libs.inject)
    implementation(project(":business:newsfeed:domain:models"))
    testImplementation(libs.mockkTest)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinTestCoroutines)
}