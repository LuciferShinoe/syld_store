package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @GetMapping(path = "/categories")
    public String GetByPage(Model model,@RequestParam(required = true) int page, @RequestParam(required = true) int limit){
       try {
           model.addAttribute("categories",categoryService.getByPage(page,limit));
           return view(model,"Categories","")
       }catch (Exception e){

       }
    }

}
