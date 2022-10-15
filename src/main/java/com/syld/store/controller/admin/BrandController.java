package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.BrandDto;
import com.syld.store.services.brand.BrandService;
import com.syld.store.ultis.SlugGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

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

    @GetMapping(path = "/{slug_brand}")

    public String BrandDetail(Model model, @PathVariable String slug_brand){
        try {
            BrandDto brandDto = brandService.getBySlugName(slug_brand);
            if(!Objects.equals(brandDto.getId(), "id")){
                model.addAttribute("id", brandService.getAll());
            }
            model.addAttribute("brand_details", brandDto);
            return view(model, "brand - details", "brand/details",this.admin_layout);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "redirect:/admin/brands/";
    }

    @GetMapping(path = "/create")
    public String Save(Model model) {
        try{
            model.addAttribute("brand", brandService.getClass());

        }catch (Exception e){
            log.info(e.getMessage());
        }
        model.addAttribute("brand", new BrandDto());
        return view(model, "Create_Brands", "brands/add",this.admin_layout);
    }
    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("brand") BrandDto brandDto, BindingResult bindingResult, Model model){

        BrandDto brandDto_ = brandService.getByName(brandDto.getBrand_name());
        if(brandDto_ != null){
            bindingResult.rejectValue("brand_name", "", "Brand name has taken !");
        }
        BrandDto brandDto__ = brandService.getBySlugName(SlugGenerator.toSlug(brandDto.getBrand_lug()));
        if(brandDto__ != null) {
            bindingResult.rejectValue("brand_slug", "", "Brand slug has taken !");
            return view(model, "Create_Brand", "brands/add", this.admin_layout);
        }
        try{
            brandService.save(brandDto);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "redirect:/admin/brands?page=1&limit=6";
    }

}
