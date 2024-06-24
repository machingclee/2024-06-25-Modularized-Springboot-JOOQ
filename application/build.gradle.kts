plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.kotlinspring.application"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}