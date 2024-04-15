pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "News"
include(":app")
include(":business:network:di")
include(":business:common:app-info")
include(":business:network:main")
include(":business:newsfeed:domain")
include(":business:newsfeed:data:di")
include(":business:newsfeed:data:main")
include(":business:newsfeed:domain:main")
include(":business:newsfeed:domain:models")
include(":business:network:data")
include(":common:kotlin")
include(":features:newsfeed:ui")
include(":features:newsfeed:presentation")
include(":common:components:ui")
include(":common:ui:theme")
include(":common:presentation:main:state")
include(":common:kotlin:di")
include(":features:newsfeed:presentation:di")
include(":common:navigation:api")
include(":common:navigation:global:presentation:models")
include(":common:navigation:global:presentation:state:main")
include(":common:navigation:global:presentation:state:di")
include(":common:navigation:global:presentation:main")
