package com.gsv28rus.sandbox.security

import com.gsv28rus.sandbox.model.User
import com.gsv28rus.sandbox.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class FirebaseAuthenticationProvider @Autowired
constructor(private val userRepository: UserRepository) : AbstractUserDetailsAuthenticationProvider() {

    override fun supports(authentication: Class<*>): Boolean {
        return FirebaseAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    @Throws(AuthenticationException::class)
    override fun additionalAuthenticationChecks(userDetails: UserDetails, authentication: UsernamePasswordAuthenticationToken) {
    }

    @Throws(AuthenticationException::class)
    override fun retrieveUser(username: String, authentication: UsernamePasswordAuthenticationToken): UserDetails {
        val authenticationToken = authentication as FirebaseAuthenticationToken
        val uid = authenticationToken.token
        var user = userRepository.findByFirebaseUid(uid)
        if (user == null) {
            user = User(uid)
            userRepository.save(user)
        }
        return FirebaseUserPrincipal(user)
    }
}
