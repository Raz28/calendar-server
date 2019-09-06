package com.gsv28rus.calendar.security

import com.google.api.client.util.Strings
import com.google.firebase.auth.FirebaseAuth
import com.gsv28rus.calendar.exception.TokenNotFoundException
import com.gsv28rus.calendar.exception.TokenNotVerifyException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import java.io.IOException
import java.util.concurrent.ExecutionException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class FirebaseAuthenticationTokenFilter(private val firebaseAuth: FirebaseAuth) : AbstractAuthenticationProcessingFilter("/**") {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authToken = request.getHeader(TOKEN_HEADER)
        if (Strings.isNullOrEmpty(authToken)) {
            throw TokenNotFoundException("Token is not found")
        }
        val task = firebaseAuth.verifyIdTokenAsync(authToken)
        val uid: String
        try {
            uid = task.get().uid
        } catch (e: InterruptedException) {
            throw TokenNotVerifyException("Token is not verified")
        } catch (e: ExecutionException) {
            throw TokenNotVerifyException("Token is not verified")
        }

        return authenticationManager.authenticate(FirebaseAuthenticationToken(uid))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        super.successfulAuthentication(request, response, chain, authResult)

        chain!!.doFilter(request, response)
    }

    companion object {
        private val TOKEN_HEADER = "X-Firebase-Auth"
    }
}
