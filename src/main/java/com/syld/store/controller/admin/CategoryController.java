package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.CategoryDto;
import com.syld.store.services.category.CategoryService;
import com.syld.store.ultis.SlugGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @GetMapping
    public String GetByPage(Model model) {
        try {
            model.addAttribute("categories", categoryService.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List Category", "category/list", this.admin_layout);
    }

    @GetMapping(path = "/{slug}")
    public String CategoryDetail(Model model, @PathVariable String slug) {
        try {
            CategoryDto categoryDto = categoryService.getBySlugName(slug);
            if (!Objects.equals(categoryDto.getParent_id(), "parent")){

                model.addAttribute("parent",categoryService.getParent(categoryDto.getParent_id()));
            }
            model.addAttribute("category_detail", categoryDto);
            return view(model, "Category - Detail", "category/detail", this.admin_layout);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/categpries/";
    }

    @GetMapping(path = "/remove/{id}")
    public String Remove(RedirectAttributes redirectAttributes, @PathVariable String id) {
        try {
            categoryService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Success!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Falied!");
        }
        return "redirect:/admin/categories/";
    }

    @GetMapping("/update/{slug}")
    public String Update(Model model, @PathVariable String slug) {
        try {
            model.addAttribute("parent_categories", categoryService.getListCategory());
            model.addAttribute("category_edit", categoryService.getBySlugName(slug));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "Edit - Category", "category/edit", this.admin_layout);
    }

    @PostMapping("/update")
    public String Update(@Valid @ModelAttribute("category_edit") CategoryDto categoryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return view(model, "Edit - Category", "category/edit", this.admin_layout);
        }
        try {
            categoryService.update(categoryDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/categories/";
    }

    @GetMapping(path = "/create")
    public String Save(Model model) {
//        get all category
        try {
            model.addAttribute("parent_categories", categoryService.getListCategory());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        model.addAttribute("category", new CategoryDto());
        return view(model, "Create - Category", "category/add", this.admin_layout);
    }

    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult bindingResult, Model model) {
        CategoryDto categoryDto_ = categoryService.getByName(categoryDto.getCategory_name());
        if (categoryDto_ != null) {
            bindingResult.rejectValue("category_name", "", "Category name has taken!");
        }
        CategoryDto categoryDto__ = categoryService.getBySlugName(SlugGenerator.toSlug(categoryDto.getCategory_slug()));
        if (categoryDto__ != null) {
            bindingResult.rejectValue("category_slug", "", "Category slug has taken!");
        }
        if (bindingResult.hasErrors()) {
            return view(model, "Create - Category", "category/add", this.admin_layout);
        }
        try {
            categoryService.save(categoryDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/categories?page=1&limit=6";
    }
}
