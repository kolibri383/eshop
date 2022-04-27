package com.example.eshop.controller

import com.example.eshop.dto.UserDTO
import com.example.eshop.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController(
        @Autowired
        val userService: UserService
) {
    @GetMapping("/new")
    fun newUser(model: Model): String{
        model.addAttribute("user", UserDTO())
        return "user"
    }
    @PostMapping("/new")
    fun saveUser(userDTO: UserDTO, model: Model):String{
        if (userService.save(userDTO))
            return "redirect:/"
        model.addAttribute("user", userDTO)
        return "user"
    }
}