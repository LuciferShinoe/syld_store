package com.syld.store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Objects;

public class BaseController {

    public String prefix = "index";

    public String layout_path = "/layout/layout";

    public String view(Model model, String title, String prefix, String layout_path) {
        if (prefix != null && layout_path != null) {
            model.addAttribute("web_content", prefix);
            this.layout_path = layout_path;
        } else {
            model.addAttribute("web_content", this.prefix);
        }
        model.addAttribute("title", title);
        return layout_path;
    }
    public String isLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()){
            if (!Objects.equals(authentication.getName(),"anonymousUser")){
                return "redirect:/home";
            }
        }
        return null;
    }
}
