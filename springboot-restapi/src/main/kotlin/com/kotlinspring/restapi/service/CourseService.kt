package com.kotlinspring.restapi.service

import com.kotlinspring.infrastructure.db.tables.daos.CourseDao
import com.kotlinspring.infrastructure.db.tables.pojos.Course
import com.kotlinspring.infrastructure.db.tables.references.COURSE
import com.kotlinspring.infrastructure.db.tables.references.TEACHER
import com.kotlinspring.restapi.data.CourseDetail
import com.kotlinspring.restapi.data.TeacherDetail
import com.kotlinspring.restapi.dto.CourseDTO
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jooq.DSLContext
import org.jooq.impl.DSL.multiset
import org.jooq.impl.DSL.select
import org.springframework.stereotype.Service

@Service
class CourseService(
    val db: DSLContext,
    val courseDao: CourseDao
) {
    val logger = KotlinLogging.logger {}
    fun addCourse(courseDTO: CourseDTO): Course? {
        val course = Course(
            name = courseDTO.name,
            category = courseDTO.category,
            approvalstatus = courseDTO.approvalStatus
        )
        courseDao.insert(course)
        logger.info {
            "Save Course is $course"
        }
        return course
    }

    // for nested records
    // https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/nested-records/
    // https://blog.jooq.org/jooq-3-15s-new-multiset-operator-will-change-how-you-think-about-sql/
    // https://stackoverflow.com/questions/32332729/jooq-query-with-nested-list
    // https://www.jooq.org/doc/latest/manual/sql-building/column-expressions/multiset-value-constructor/
    // https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
    // for transaction: https://www.jooq.org/doc/latest/manual/sql-execution/transaction-management/
    fun getTeachers(teacherId: Int): TeacherDetail? {
        val courseResult =
            db
                .select(
                    TEACHER.NAME.`as`("teacherName"),
                    multiset(
                        select(
                            COURSE.NAME.`as`("courseName"),
                            COURSE.CATEGORY.`as`("courseCategory")
                        )
                            .from(COURSE)
                            .where(COURSE.TEACHERID.eq(TEACHER.ID))
                    ).`as`("courses").convertFrom { it.into(CourseDetail::class.java) }
                )
                .from(TEACHER)
                .where(TEACHER.ID.eq(teacherId))
                .fetchOneInto(TeacherDetail::class.java)
        return courseResult
    }
}
