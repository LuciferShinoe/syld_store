package com.syld.store.controller;


import com.syld.store.dto.UserClientDto;
import com.syld.store.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController{

    private final UserService userService;
    private final MessageSource messageSource;

    @GetMapping(path = "/login")
    public String Login(Model model , @RequestParam(required = false) String error){
        if (error != null) model.addAttribute("message", messageSource.getMessage(error,null, LocaleContextHolder.getLocale()));
        return view(model,"Login Page ","login","layout/client_layout");
    }

    @GetMapping(path = "/register")
    public String Register(Model model , @RequestParam(required = false) String error){
        if (error != null) model.addAttribute("message", messageSource.getMessage(error,null, LocaleContextHolder.getLocale()));
        model.addAttribute("user_reg", new UserClientDto());
        return view(model,"Register Page ","register","layout/client_layout");
    }


    @PostMapping(path = "/register")
    public String Register(@Valid @ModelAttribute("user_reg") UserClientDto userClientDto, BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) {
        if(!Objects.equals(userClientDto.getPassword(), userClientDto.getRePassword())){
            bindingResult.rejectValue("RePassword", "error.userClient", "Mật khẩu không trùng khớp !");
        }
        if(bindingResult.hasErrors()) {
            return view(model,"Register","register","layout/client_layout");
        }
        try {
            userService.save(userClientDto);
            model.addAttribute("message","Tạo tài khoản mới thành công !");
        }catch (Exception e) {
            model.addAttribute("message", "Tạo tài khoản mới không thành công !");
        }

        return "redirect:/auth/register";
    }

    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/auth/logout";
    }

}
