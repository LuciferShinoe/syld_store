package com.syld.store.controller;


import com.syld.store.dto.UserClientDto;
import com.syld.store.services.user.UserClientService;
import com.syld.store.services.user.UserDetailServiceIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/auth")
public class AuthController extends BaseController{

    @Autowired
    UserClientService userClientService;

    @GetMapping(path = "/login")
    public String Login(Model model){
        return view(model,"Login Page ","login","layout/client_layout");
    }


    @RequestMapping("/Register")

    public String Register(Model model) { return  view(model, "Register Page", "Register", "layout/client_layout");}

    public String Register(Model model) {
        return  view(model, "Register Page", "Register", "layout/client_layout");



    @PostMapping(value = "Save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String Save(@Valid @ModelAttribute("UserClient") UserClientDto userClientDto,
                       BindingResult bindingResult, RedirectAttributes model, Model m) {
        if(!Object.equals(userClientDto.getPassword(), userClientDto.getRePassword())){
            bindingResult.rejectValue("RePassword", "error.userClient", "Mật khẩu không trùng khớp !");
        }
        if(bindingResult.hasErrors()) {
            return "auth/Register";
        }
        try {


            userClientService.Save(userClientDto);
            model.addAttribute("message","Tạo tài khoản mới thành công !");
        }catch (Exception e) {
            model.addAttribute("message", "Tạo tài khoản mới không thành công !");
        }

        return "redirect:/auth/Register";
    }

}
