package com.kotlinspring.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(
    basePackages = [
        "com.kotlinspring.restapi",
        "com.kotlinspring.infrastructure",
        "com.kotlinspring.domain",
        "com.kotlinspring.application"
    ]
)
class CourseCatelogServiceApplication

fun main(args: Array<String>) {
    runApplication<CourseCatelogServiceApplication>(*args)
}