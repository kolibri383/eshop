package com.example.eshop.service

import com.example.eshop.dao.UserDAO
import com.example.eshop.dto.UserDTO
import com.example.eshop.model.Role
import com.example.eshop.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        @Autowired
        val userDAO: UserDAO,
        @Autowired
        val passwordEncoder: PasswordEncoder
) : UserService {


    override fun save(userDTO: UserDTO): Boolean {
        if (userDTO.password != userDTO.matchingPassword)
            throw RuntimeException("Password is not equals")
        val user = User(
                name = userDTO.name,
                password = passwordEncoder.encode(userDTO.password),
                email = userDTO.email,
                role = Role.CLIENT,
                cart = null
        )
        userDAO.save(user)
        return true
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userDAO.findFirstByName(username!!)
                ?: throw UsernameNotFoundException("User not found with username: $username")
        val roles = ArrayList<GrantedAuthority>().apply {
            add(SimpleGrantedAuthority(user.role.name))
        }
        return org.springframework.security.core.userdetails.User(
                user.name,
                user.password,
                roles
        )
    }
}