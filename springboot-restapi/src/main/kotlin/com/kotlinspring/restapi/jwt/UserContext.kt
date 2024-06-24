package com.kotlinspring.restapi.jwt

class UserContext {
    private val threadLocal: ThreadLocal<TokenPayload?> = ThreadLocal<TokenPayload?>()

    fun clear() {
        threadLocal.set(null)
    }

    fun setUser(tokenPayload: TokenPayload?) {
        threadLocal.set(tokenPayload)
    }

    fun getUser(): TokenPayload? {
        return threadLocal.get()
    }

    companion object {
        val instance: UserContext = UserContext()
    }
}