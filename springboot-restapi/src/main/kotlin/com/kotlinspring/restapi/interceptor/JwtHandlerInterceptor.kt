package com.kotlinspring.restapi.interceptor

import com.kotlinspring.restapi.jwt.Jwt
import com.kotlinspring.restapi.jwt.TokenPayload
import com.kotlinspring.restapi.jwt.UserContext
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor


class JwtHandlerInterceptor(
    private val jwt: Jwt
) : HandlerInterceptor {
    private val authHeader: String = "Authorization"
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        UserContext.instance.clear()
        val accessToken = request.getHeader(authHeader).replace("Bearer ", "")
        if (accessToken.isEmpty()) {
            throw Exception("AccessToken cannot be empty")
        }
        val token: TokenPayload = jwt.parseAndVerifyToken(accessToken)
        UserContext.instance.setUser(token)
        return true
    }
}