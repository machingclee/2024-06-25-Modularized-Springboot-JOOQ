plugins {
    id("java-library")
}

val jooqVersion = "3.19.9"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jooq:jooq:$jooqVersion")
}