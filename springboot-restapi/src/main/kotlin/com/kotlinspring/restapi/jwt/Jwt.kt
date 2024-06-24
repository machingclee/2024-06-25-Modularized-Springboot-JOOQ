package com.kotlinspring.restapi.jwt

import at.favre.lib.crypto.bcrypt.BCrypt
import com.alibaba.fastjson.JSONObject
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

data class TokenPayload(
    val id: UUID,
    val username: String,
    val email: String,
    val expiredAt: Long
)

@Service
class Jwt private constructor() {
    @Value("\${jwt.secretKey}")
    var secretKey: String? = null

    @Value("\${jwt.expirationTime}")
    var expirationTime: Long = 0L

    private val saltLength = 10
    var secret: SecretKey? = null

    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(saltLength, password.toCharArray())
    }

    fun comparePasswordWithHash(password: String, bcryptHash: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), bcryptHash).verified
    }

    private fun initSecret() {
        if (secret == null) {
            secretKey?.let {
                secret = Keys.hmacShaKeyFor(it.toByteArray(Charsets.UTF_8))
                println("inited! $it")
            }
        }
    }

    fun createToken(userId: UUID, username: String, email: String): Pair<String, TokenPayload> {
        val expiredAt = expirationTime + System.currentTimeMillis()
        val payload = TokenPayload(
            userId,
            username,
            email,
            expiredAt,
        )
        val json = JSONObject.toJSON(payload).toString()
        initSecret()
        return Pair<String, TokenPayload>(
            Jwts.builder().setSubject(json).signWith(secret).compact(),
            payload
        )
    }

    fun parseAndVerifyToken(token: String?): TokenPayload {
        initSecret()
        val jws: Jws<Claims> = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token)
        val subject = jws.body.subject
        val tokenPayload = JSONObject.parseObject(subject, TokenPayload::class.java)
        if (System.currentTimeMillis() > tokenPayload.expiredAt) {
            throw Exception("token expired")
        }
        return tokenPayload
    }
}