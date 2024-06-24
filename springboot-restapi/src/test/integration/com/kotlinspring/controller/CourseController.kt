package com.kotlinspring.controller

import com.restapi.config.JOOQContextConfig
import com.kotlinspring.db.enums.Courseapprovalstatus
import com.kotlinspring.db.tables.daos.CourseDao
import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.repository.CourseRepository
import com.kotlinspring.service.CourseService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest

import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest
@ActiveProfiles("default", "test")
@ContextConfiguration(classes = [com.restapi.config.JOOQContextConfig::class])
@Import(com.restapi.controller.CourseController::class, CourseService::class, CourseRepository::class, CourseDao::class)
class CourseControllerIntegrationTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun addCourse() {
        val courseDTO = CourseDTO(null, "Build Nice Course", "James Lee", Courseapprovalstatus.APPROVED)
        val savedCourseDTO = webTestClient.post().uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        Assertions.assertTrue { savedCourseDTO!!.id != null }
        Assertions.assertTrue { savedCourseDTO!!.approvalStatus == Courseapprovalstatus.APPROVED }
        Assertions.assertTrue { savedCourseDTO!!.approvalStatus != Courseapprovalstatus.PENDING }
    }
}