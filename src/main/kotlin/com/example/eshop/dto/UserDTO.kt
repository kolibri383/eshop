package com.example.eshop.dto

data class UserDTO(
        var name: String = "",
        var password: String = "",
        var matchingPassword: String = "",
        var email: String = ""
)