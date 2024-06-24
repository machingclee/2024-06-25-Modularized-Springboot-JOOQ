//package com.kotlinspring.restapi.course
//
//import com.kotlinspring.db.tables.pojos.Course
//import com.kotlinspring.db.tables.references.COURSE
//import com.kotlinspring.event.course.CreateCourseEvent
//import com.kotlinspring.event.course.CreateCoursePayload
//import io.github.oshai.kotlinlogging.KotlinLogging
//import org.jooq.DSLContext
//import org.springframework.context.ApplicationListener
//import org.springframework.core.annotation.Order
//import org.springframework.stereotype.Component
//
//fun throwException() {
//    throw Exception("Intentionally throw an exception")
//}

//
//@Order(1)
//@Component
//class CreateCourseListener(
//    private val db: DSLContext
//) : ApplicationListener<CreateCourseEvent> {
//    val logger = KotlinLogging.logger {}
//    override fun onApplicationEvent(event: CreateCourseEvent) {
//        val payload = event.source as CreateCoursePayload
//        val courseDTO = payload.courseDTO
//        val latch = payload.latch
//        val resultCallback = payload.resultCallback
//        db.transaction { trx ->
//            val ctx = trx.dsl()
//            try {
//                val result = courseDTO.let {
//                    ctx.insertInto(COURSE, COURSE.NAME, COURSE.APPROVALSTATUS, COURSE.CATEGORY)
//                        .values(it.name, it.approvalStatus, it.category)
//                        .returning()
//                        .fetchOneInto(Course::class.java)
//                }
//                resultCallback?.invoke(result)
//            } catch (e: Exception) {
//                logger.error { e }
//                throw e
//            } finally {
//                latch.countDown()
//            }
//        }
//    }
//}