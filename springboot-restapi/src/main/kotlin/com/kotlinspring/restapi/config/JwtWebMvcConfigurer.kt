package com.kotlinspring.restapi.config


import com.kotlinspring.restapi.interceptor.JwtHandlerInterceptor
import com.kotlinspring.restapi.jwt.Jwt
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class JwtWebMvcConfigurer(
    private val jwt: Jwt
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(JwtHandlerInterceptor(jwt)).addPathPatterns("/course/**")
    }
}