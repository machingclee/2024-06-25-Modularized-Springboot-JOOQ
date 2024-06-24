package com.kotlinspring.restapi.event.course

import com.kotlinspring.infrastructure.db.tables.pojos.Course
import com.kotlinspring.restapi.dto.CourseDTO
import org.springframework.context.ApplicationEvent
import java.util.concurrent.CountDownLatch

data class CreateCoursePayload(
    val courseDTO: CourseDTO,
    val latch: CountDownLatch,
    val resultCallback: ((Course?) -> Unit)?
)

data class CreateCourseEvent(private val args: CreateCoursePayload) : ApplicationEvent(args)