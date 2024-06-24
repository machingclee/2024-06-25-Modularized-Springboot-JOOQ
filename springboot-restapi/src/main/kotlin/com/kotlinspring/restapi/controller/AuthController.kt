package com.kotlinspring.restapi.controller

import com.kotlinspring.infrastructure.db.tables.daos.UserDao
import com.kotlinspring.infrastructure.db.tables.pojos.User
import com.kotlinspring.restapi.dto.LoginResponse
import com.kotlinspring.restapi.dto.LoginUserDto
import com.kotlinspring.restapi.dto.RegisterUserDTO
import com.kotlinspring.restapi.jwt.Jwt
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val jwt: Jwt,
    val userDAO: UserDao
) {
    @PostMapping("/signup")
    fun register(@RequestBody registerUserDto: RegisterUserDTO): ResponseEntity<User> {
        val passwordHash = jwt.hashPassword(registerUserDto.password)
        val user = User(
            null,
            firstname = registerUserDto.firstName,
            lastname = registerUserDto.lastName,
            passwordhash = passwordHash,
            email = registerUserDto.email
        )
        userDAO.insert(user)
        println("secret!!!! ${jwt.secretKey}")
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    fun authenticate(@RequestBody loginUserDto: LoginUserDto): ResponseEntity<LoginResponse> {
        val user = userDAO.fetchByEmail(loginUserDto.email).firstOrNull()
            ?: throw Exception("Username or password is incorrect")

        val loginPassword = loginUserDto.password
        val valid = jwt.comparePasswordWithHash(loginPassword, user.passwordhash)
        if (!valid) {
            throw Exception("Username or password is incorrect")
        }
        val username = "${user.firstname} ${user.lastname}"
        val result = jwt.createToken(userId = user.id!!, email = loginUserDto.email, username = username)
        val token = "Bearer ${result.first}"
        val payload = result.second
        val loginResponse = LoginResponse(token = token, expiresAt = payload.expiredAt)

        return ResponseEntity.ok(loginResponse)
    }
}