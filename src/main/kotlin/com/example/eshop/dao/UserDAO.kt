package com.example.eshop.dao

import com.example.eshop.model.User
import org.springframework.data.repository.CrudRepository

interface UserDAO : CrudRepository<User, Long> {
    fun findFirstByName(name: String):User?
}