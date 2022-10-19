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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/brands")
public class BrandController extends BaseController {

    private final BrandService brandService;

    @GetMapping
    public String GetByPage(Model model) {
        try {
            model.addAttribute("brands", brandService.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List Brand", "brand/list", this.admin_layout);
    }

    @GetMapping(path = "/{slug}")
    public String BrandDetail(Model model, @PathVariable String slug) {
        try {
            model.addAttribute("brand_detail", brandService.getBySlugName(slug));
            return view(model, "Brand - detail", "brand/detail", this.admin_layout);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/brands/";
    }

    @GetMapping(path = "/create")
    public String Save(Model model) {
        try {
            model.addAttribute("brands", new BrandDto());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "Create_Brands", "brand/add", this.admin_layout);
    }

    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("brands") @NotNull BrandDto brandDto, BindingResult bindingResult, Model model) {

        BrandDto brandDto_ = brandService.getByName(brandDto.getBrand_name());
        if (brandDto_ != null) {
            bindingResult.rejectValue("brand_name", "", "Brand name has taken !");
        }
        BrandDto brandDto__ = brandService.getBySlugName(SlugGenerator.toSlug(brandDto.getBrand_slug()));
        if (brandDto__ != null) {
            bindingResult.rejectValue("brand_slug", "", "Brand slug has taken !");
        }
        if (bindingResult.hasErrors()) {
            return view(model, "Create_Brand", "brand/add", this.admin_layout);
        }
        try {
            brandService.save(brandDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/brands?page=1&limit=6";
    }

    @GetMapping(path = "/update/{slug}")
    public String Update(Model model, @PathVariable String slug) {
        try {
            model.addAttribute("brand_edit", brandService.getBySlugName(slug));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "Edit - Brand", "brand/edit", this.admin_layout);
    }

    @PostMapping(path = "/update")
    public String Update(@Valid @ModelAttribute("brand_edit") BrandDto brandDto, BindingResult bindingResult, Model model) {

        BrandDto brandDto_ = brandService.getByNameNotSame(brandDto.getBrand_name(),brandDto.getId());
        if (brandDto_ != null) {
            bindingResult.rejectValue("brand_name", "", "Brand name has taken !");
        }
        BrandDto brandDto__ = brandService.getBySlugNameNotSame(SlugGenerator.toSlug(brandDto.getBrand_slug()),brandDto.getId());
        if (brandDto__ != null) {
            bindingResult.rejectValue("brand_slug", "", "Brand slug has taken !");
        }

//        chua bat loi trung slug trung ten
        if (bindingResult.hasErrors()) {
            return view(model, "Edit - Brand", "brand/edit", this.admin_layout);
        }
        try {
            brandService.update(brandDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/brands/";
    }

    @GetMapping(path = "remove/{id}")
    public String Remove(RedirectAttributes redirectAttributes, @PathVariable String id) {
        try {
            brandService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Success");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "Falied");
        }
        return "redirect:/admin/brands";
    }
}
