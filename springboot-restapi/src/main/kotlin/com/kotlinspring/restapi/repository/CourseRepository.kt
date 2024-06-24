package com.kotlinspring.restapi.repository

import com.kotlinspring.infrastructure.db.tables.daos.CourseDao
import com.kotlinspring.infrastructure.db.tables.pojos.Course
import com.kotlinspring.infrastructure.db.tables.references.COURSE
import org.jooq.DSLContext
import org.springframework.stereotype.Repository


@Repository
class CourseRepository(
    private val db: DSLContext,
    private val courseDao: CourseDao
) {
    fun save(course: Course): Course? {
//        courseDao.insert(course)
        val result = db.insertInto(COURSE, COURSE.NAME, COURSE.CATEGORY, COURSE.APPROVALSTATUS)
            .values(course.name, course.category, course.approvalstatus)
            .returning()
            .fetchOneInto(Course::class.java)

        return result
    }
}