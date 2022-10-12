package com.syld.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/auth")
public class AuthController extends BaseController{

    @GetMapping(path = "/login")
    public String Login(Model model){
        return view(model,"Login Page ","login","layout/client_layout");
    }


    @RequestMapping("/Register")
    public String Register(Model model) {
        return  view(model, "Register Page", "Register", "layout/client_layout");

    }
}
