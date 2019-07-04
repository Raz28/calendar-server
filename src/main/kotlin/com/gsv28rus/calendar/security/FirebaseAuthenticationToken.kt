package com.gsv28rus.calendar.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class FirebaseAuthenticationToken(val token: String) : UsernamePasswordAuthenticationToken(null, null) {
    companion object {
        private const val serialVersionUID = 1L
    }
}