package com.kotlinspring.restapi.controller

import com.kotlinspring.infrastructure.db.tables.daos.CourseDao
import com.kotlinspring.infrastructure.db.tables.pojos.Course
import com.kotlinspring.restapi.dto.CourseDTO
import com.kotlinspring.restapi.dto.GetTeacherResponseDTO
import com.kotlinspring.restapi.dto.ResponseDTO
import com.kotlinspring.restapi.event.EventPublisher
import com.kotlinspring.restapi.event.course.CreateCourseEvent
import com.kotlinspring.restapi.event.course.CreateCoursePayload
import com.kotlinspring.restapi.jwt.UserContext
import com.kotlinspring.restapi.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.concurrent.CountDownLatch

@RestController
@RequestMapping("/course")
class CourseController(
    val courseService: CourseService,
    val eventPublisher: EventPublisher,
    val courseDao: CourseDao
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDTO: CourseDTO): Course? {
        val latch = CountDownLatch(1)
        val user = UserContext.instance.getUser()
        var savedResult: Course? = null
        val createCoursePayload = CreateCoursePayload(
            courseDTO,
            latch
        ) { dbResult: Course? -> savedResult = dbResult }
        val createCourseEvent = CreateCourseEvent(createCoursePayload)
        eventPublisher.publishEvent(createCourseEvent)
        latch.await()
        println("latch.await() unlocked!")

        return savedResult
    }

    @GetMapping("/course/{id}")
    fun getCourse(
        @PathVariable(name = "id") id: String
    ): ResponseEntity<ResponseDTO<Map<String, Any?>>> {
//        val user = userService.authData
        val token = UserContext.instance.getUser()
        val id = UUID.fromString("f200aa37-2430-46ae-9f3a-042ac23b0a0c")
        val course = courseDao.fetchOneById(id)
        val user = mapOf("username" to token?.username, "email" to token?.email)
        return ResponseEntity.ok(ResponseDTO(result = mapOf("requestUser" to user, "course" to course)))
    }

    @GetMapping
    @RequestMapping("/teachers/{teacherId}")
    fun getTeacher(@PathVariable(name = "teacherId") teacherId: Int): GetTeacherResponseDTO {
        val result = courseService.getTeachers(teacherId)
        val numOfCourses = (result?.courses ?: listOf<Any>()).count()
        return GetTeacherResponseDTO(numOfCourses, result)
    }
}