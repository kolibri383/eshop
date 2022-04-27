package com.example.eshop.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {

    @RequestMapping("","/")
    fun index() = "index"


    @RequestMapping("/login")
    fun login() = "login"
}