package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.ProductDto;
import com.syld.store.services.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//this res controller to test
@RestController
// if using view change it to controller
@RequestMapping(path = "/admin/products")
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @GetMapping("/create")
    public String Create(Model model){
        model.addAttribute("productDto", new ProductDto());
        return "create";
    }

    @GetMapping("/list")
    public String getByPage(@PagingParam(path = "product") ResponseDataTableDto responseDataTableDto) {
        try {
            productService.list(responseDataTableDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(@Valid @ModelAttribute ProductDto entity) {

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@Valid @ModelAttribute ProductDto productDto, @PathVariable String id) {
//        try {
//
//        } catch (Exception e) {
//
//        }
//    }

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
