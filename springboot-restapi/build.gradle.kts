repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

group = "com.kotlinspring.restapi"
version = "0.0.1-SNAPSHOT"


repositories {
    google()
    mavenCentral()
}


plugins {
    id("custom-java-base")
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("db-common")

    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:2.0.0"))
    implementation(project(":application"))
    implementation(project(":infrastructure"))

    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("com.alibaba:fastjson:2.0.51")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")


    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
//    testImplementation("io.projectreactor:reactor-test")
}
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("src/test/integration", "src/test/unit"))
        }
    }
}
// replacement of application
springBoot {
    mainClass = "com.kotlinspring.restapi.CourseCatelogServiceApplicationKt"
}


