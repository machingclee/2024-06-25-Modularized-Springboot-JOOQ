import org.jooq.meta.jaxb.*
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.Target

group = "com.kotlinspring.infrastructure"
version = "unspecified"
val srcPackage = "com.kotlinspring.infrastructure"
val jooqVersion = "3.19.9"

plugins {
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jooq.jooq-codegen-gradle") version "3.19.10"
    id("db-common")
}

repositories {
    google()
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

buildscript {
    val jooqVersion = "3.19.9"
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jooq:jooq-codegen:$jooqVersion")
        classpath("org.postgresql:postgresql:42.3.5")
    }
}

dependencies {
    compileOnly("org.jooq:jooq-codegen:$jooqVersion")
    runtimeOnly("org.postgresql:postgresql:42.3.5")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.create("generate") {
    GenerationTool.generate(
        Configuration()
            .withJdbc(
                Jdbc()
                    .withDriver("org.postgresql.Driver")
                    .withUrl("jdbc:postgresql://localhost:5432/pgdb")
                    .withUser("pguser")
                    .withPassword("pguser")
            )
            .withGenerator(
                Generator()
                    .withName("org.jooq.codegen.KotlinGenerator")
                    .withDatabase(Database().withInputSchema("public"))
                    .withGenerate(
                        Generate()
                            .withPojos(true)
                            .withDaos(true)
                            .withSpringAnnotations(true)
                            .withJpaAnnotations(true)
                            .withKotlinNotNullPojoAttributes(true)
                            .withKotlinDefaultedNullablePojoAttributes(true)
                    )
                    .withTarget(
                        Target()
                            .withPackageName("$srcPackage.db")
                            .withDirectory("$projectDir/src/main/kotlin")
                    )
            )
    )
}
