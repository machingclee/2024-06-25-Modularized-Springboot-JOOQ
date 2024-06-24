package com.kotlinspring.restapi.service//package com.kotlinspring.service
//
//import com.kotlinspring.data.TokenParams
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Service
//
//@Service
//class UserService {
//    val authData: TokenParams
//        get() {
//            val auth = SecurityContextHolder.getContext().authentication
//            val tokenDetail = auth.principal as TokenParams
//            return tokenDetail
//        }
//}