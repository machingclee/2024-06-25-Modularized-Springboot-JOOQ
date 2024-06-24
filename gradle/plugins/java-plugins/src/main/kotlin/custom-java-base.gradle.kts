import org.gradle.kotlin.dsl.repositories

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    id("java-library")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}



