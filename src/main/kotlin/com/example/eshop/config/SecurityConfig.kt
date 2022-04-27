package com.example.eshop.config

import com.example.eshop.model.Role
import com.example.eshop.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
class SecurityConfig(
        @Autowired
        val userService: UserService
) : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    private fun authenticationProvider(): AuthenticationProvider =
            DaoAuthenticationProvider().apply {
                setUserDetailsService(userService)
                setPasswordEncoder(passwordEncoder())
            }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
            BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                ?.antMatchers("/users/new")?.hasAuthority(Role.ADMIN.name)
                ?.anyRequest()?.permitAll()
                ?.and()
                    ?.formLogin()
                ?.loginPage("/login")
                ?.permitAll()
                ?.and()
                    ?.logout()?.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                    ?.logoutSuccessUrl("/")?.deleteCookies("JSESSIONID")
                    ?.invalidateHttpSession(true)
                ?.and()
                    ?.csrf()?.disable()
    }
}