package com.gsv28rus.calendar.security

import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UserWebSecurityConfig
@Autowired constructor(private val authenticationProvider: FirebaseAuthenticationProvider, private val firebaseAuth: FirebaseAuth) : WebSecurityConfigurerAdapter() {

    @Bean
    public override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf<AuthenticationProvider>(authenticationProvider))
    }

    fun authenticationTokenFilterBean(): FirebaseAuthenticationTokenFilter {
        val authenticationTokenFilter = FirebaseAuthenticationTokenFilter(firebaseAuth)
        authenticationTokenFilter.setAuthenticationManager(authenticationManager())
        authenticationTokenFilter.setAuthenticationSuccessHandler { request, response, authentication -> }
        return authenticationTokenFilter
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers(HttpMethod.OPTIONS)
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {

        httpSecurity.antMatcher("/users/**")
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/users/signIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
        httpSecurity.addFilterBefore(ExceptionHandlerFilter(), FirebaseAuthenticationTokenFilter::class.java)
    }


}
