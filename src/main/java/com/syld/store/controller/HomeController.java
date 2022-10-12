package com.syld.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class HomeController extends BaseController{

    private final String layout_path = "layout/client_layout";

    @GetMapping
    public String Index(Model model) {
        return view(model,"Home","index",this.layout_path);
    }
}

