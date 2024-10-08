plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    kotlin("native.cocoapods") version "2.1.0-Beta1"
    kotlin("jvm") version "2.1.0-Beta1"
    kotlin("plugin.serialization") version "2.1.0-Beta1"
}