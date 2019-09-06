package com.gsv28rus.calendar.security

import com.gsv28rus.calendar.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class FirebaseUserPrincipal(var user: User?) : UserDetails {

    private val enabled = true
    private val credentialsNonExpired = true
    private val accountNonLocked = true
    private val accountNonExpired = true
    private val password: String? = null
    private var username: String? = null
    var id: String? = null

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return user?.id.toString()
    }

    fun setUsername(username: String) {
        this.username = username
    }

    companion object {
        val serialVersionUID = 1L
    }
}
