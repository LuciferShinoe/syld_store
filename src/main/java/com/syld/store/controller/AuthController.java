package com.syld.store.controller;


import com.syld.store.config.message.MessageConfig;
import com.syld.store.dto.UserClientDto;
import com.syld.store.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController extends BaseController {

    private final UserService userService;

    @Autowired
    MessageConfig messageConfig;
    @GetMapping(path = "/login")
    public String Login(Model model, @RequestParam(required = false,value = "error") String error,HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        log.info("here");
        if (inputFlashMap != null) {
            model.addAttribute("message", inputFlashMap.get("message"));
        }
        if (this.isLogin() != null) {
            return this.isLogin();
        }
        model.addAttribute("user",new UserClientDto());
        if (error != null)
            model.addAttribute("message",getAuthErr(error));
        return view(model, "Login Page ", "login", "layout/client_layout");
    }

    @GetMapping(path = "/register")
    public String Register(Model model, @RequestParam(required = false) String error, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            model.addAttribute("message", inputFlashMap.get("message"));
        }
        if (this.isLogin() != null) {
            return this.isLogin();
        }
        if (error != null)
            model.addAttribute("message", getAuthErr(error));
        model.addAttribute("user_reg", new UserClientDto());
        return view(model, "Register Page ", "register", "layout/client_layout");
    }


    @PostMapping(path = "/register")
    public String Register(@Valid @ModelAttribute("user_reg") UserClientDto userClientDto, BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) {
        try {
            if (!Objects.equals(userClientDto.getPassword(), userClientDto.getRePassword())) {
                bindingResult.rejectValue("RePassword", "error.userClient", "Mật khẩu không trùng khớp !");
            }
            if (bindingResult.hasErrors()) {
                return view(model, "Register", "register", "layout/client_layout");
            }
            try {
                userService.save(userClientDto);
                redirectAttr.addFlashAttribute("message", "Tạo tài khoản mới thành công !");
                return "redirect:/auth/login";
            } catch (Exception e) {
                log.info(e.getMessage());
                redirectAttr.addFlashAttribute("message", "Tạo tài khoản mới không thành công !");
            }
        } catch (Exception e) {
            log.info("Errors  : {}", e.getMessage());
        }
        return "redirect:/auth/register";
    }

    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/logout";
    }

}
