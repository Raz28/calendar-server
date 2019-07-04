package com.gsv28rus.sandbox.security

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.gsv28rus.sandbox.exception.TokenNotFoundException
import com.gsv28rus.sandbox.exception.TokenNotVerifyException
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionHandlerFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    public override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: TokenNotFoundException) {
            val errorResponse = ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.message!!)
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.write(convertObjectToJson(errorResponse)!!)
        } catch (e: TokenNotVerifyException) {
            val errorResponse = ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.message!!)
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.write(convertObjectToJson(errorResponse)!!)
        }

    }

    @Throws(JsonProcessingException::class)
    fun convertObjectToJson(`object`: Any?): String? {
        if (`object` == null) {
            return null
        }
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(`object`)
    }
}
