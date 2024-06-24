package com.kotlinspring.restapi.exceptionHandler

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): Map<String, Any?> {
        return mapOf(
            "success" to false,
            "errorMessage" to e.message
        )
    }
}