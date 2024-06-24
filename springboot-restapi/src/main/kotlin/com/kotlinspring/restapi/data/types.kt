package com.kotlinspring.restapi.data

import java.io.Serializable

data class CourseDetail(
    val courseName: String,
    val courseCategory: String
)

data class TeacherDetail(
    val teacherName: String,
    val courses: List<CourseDetail>?
)

data class Teacher(
    val teacherName: String
)