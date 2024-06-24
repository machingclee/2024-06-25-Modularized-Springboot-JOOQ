rootProject.name = "course-catelog-service"

pluginManagement {
    repositories.gradlePluginPortal()
    includeBuild("gradle/plugins")
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories.mavenCentral()
}

include("springboot-restapi")
include("domain")
include("application")
include("infrastructure")