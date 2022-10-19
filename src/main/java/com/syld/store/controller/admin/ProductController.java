package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.ProductDto;
import com.syld.store.services.brand.BrandService;
import com.syld.store.services.category.CategoryService;
import com.syld.store.services.product.ProductService;
import com.syld.store.services.size.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//this res controller to test
@Controller
// if using view change it to controller
@RequestMapping(path = "/admin/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController extends BaseController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final BrandService brandService;
    private final SizeService sizeService;

    @GetMapping
    public String getAll(Model model) {
        try {
            model.addAttribute("list", productService.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List Product", "product/list", this.admin_layout);
    }
    @GetMapping("/{id}")
    public String ProductDetail(Model model,@PathVariable String id){
        model.addAttribute("single_product",productService.getById(id));
        return view(model,"Product - Detail","product/detail",this.admin_layout);
    }

    @GetMapping(path = "/create")
    public String create(Model model) {
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("categories", categoryService.getByParentList());
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("product", new ProductDto());
        return view(model, "Add Product", "product/add", this.admin_layout);
    }


    @PostMapping(path = "/create")
    public String save_entity(@Valid @ModelAttribute("product") ProductDto entity, BindingResult bindingResult, Model model) {
        ProductDto has_product = productService.findByName(entity.getProduct_name());
        ProductDto has_slug = productService.findBySlug(entity.getSlug());
        if (has_product != null) {
            bindingResult.rejectValue("product_name", "", "Product Name Has Exits!");
        }
        if (has_slug != null) {
            bindingResult.rejectValue("slug", "", "Slug Has Exits!");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAll());
            model.addAttribute("categories", categoryService.getByParentList());
            model.addAttribute("sizes", sizeService.getAll());
            return view(model, "Add Product", "product/add", this.admin_layout);
        }
        try {
            productService.save(entity);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/products";
    }


    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable String id){
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("categories", categoryService.getByParentList());
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("product",new ProductDto());
        model.addAttribute("single_product",productService.getById(id));
        return view(model,"Edit - Product","product/edit",this.admin_layout);
    }



    @PostMapping("/update")
    public String update_entity(@Valid @ModelAttribute("product") ProductDto entity,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            return view(model,"Edit - Product","product/edit",this.admin_layout);
        }
        try {
            productService.update(entity);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "redirect:/admin/products";
    }

    public ResponseEntity<?> remove(String Id) {
        return null;
    }
}
