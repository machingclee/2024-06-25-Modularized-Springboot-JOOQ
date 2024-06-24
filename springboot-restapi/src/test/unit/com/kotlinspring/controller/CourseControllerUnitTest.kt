package com.kotlinspring.controller

import com.restapi.data.CourseDetail
import com.restapi.data.TeacherDetail
import com.kotlinspring.dto.GetTeacherResponseDTO
import com.kotlinspring.service.CourseService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [com.restapi.controller.CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest(
    @Autowired var webTestClient: WebTestClient
) {
    @MockkBean
    lateinit var courseServiceMock: CourseService

    @Test
    fun getTeachers() {
        val name = "James Lee"

        every {
            courseServiceMock.getTeachers(any())
        } returns _root_ide_package_.com.restapi.data.TeacherDetail("James", listOf<com.restapi.data.CourseDetail>())

        val result = webTestClient.get().uri("/v1/teachers/${1}")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(GetTeacherResponseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue { result?.numOfClass == 0 }
    }
}