package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.services.brand.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/brands")
public class BrandController extends BaseController {

    private final BrandService brandService;

    @GetMapping
    public String GetByPage(Model model){
        try {
            model.addAttribute("brands", brandService.getClass());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return view(model, "List Brand", "brand/list", this.admin_layout );

    }

}
