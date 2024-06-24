package com.kotlinspring.restapi.dto

import com.kotlinspring.infrastructure.db.enums.Courseapprovalstatus
import com.kotlinspring.restapi.data.TeacherDetail
import java.util.*


data class CourseDTO(
    val id: UUID?,
    val name: String,
    val category: String,
    val approvalStatus: Courseapprovalstatus
)

data class RegisterUserDTO(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)

data class LoginUserDto(
    val email: String,
    val password: String,
)

data class GetTeacherResponseDTO(
    val numOfClass: Int,
    val teachDetail: TeacherDetail?
)

data class LoginResponse(
    var token: String? = null,
    var expiresAt: Long? = null
)

data class ResponseDTO<T>(
    val success: Boolean = true,
    val result: T
)