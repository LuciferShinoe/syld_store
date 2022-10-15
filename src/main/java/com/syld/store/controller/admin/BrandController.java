package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.services.brand.BrandService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class BrandController extends BaseController {

    private final BrandService brandService;

    @GetMapping
    public String GetByPage(Model model){
        try {
            model.addAtribute("brands", )
        }
    }

}
