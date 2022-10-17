package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.ProductDto;
import com.syld.store.services.brand.BrandService;
import com.syld.store.services.category.CategoryService;
import com.syld.store.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this res controller to test
@Controller
// if using view change it to controller
@RequestMapping(path = "/admin/products")
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping(path = "/create")
    public String create(Model model) {
        model.addAttribute("brands",brandService.getAll());
        model.addAttribute("categories", categoryService.getByParentList());
        model.addAttribute("product", new ProductDto());
        return view(model, "Add Product", "product/add", this.admin_layout);
    }

    public ResponseEntity<?> save_entity(ProductDto entity) {
        return null;
    }

    public ResponseEntity<?> update_entity(ProductDto entity) {
        return null;
    }

    public ResponseEntity<?> remove(String Id) {
        return null;
    }

}
