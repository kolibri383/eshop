package com.example.eshop.service

import com.example.eshop.dto.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService{ //for security
    fun save(userDTO: UserDTO):Boolean
}