package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.ColorDto;
import com.syld.store.services.color.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "admin/colors")
public class ColorController extends BaseController {

    private final ColorService colorService;

    @GetMapping
    public String GetByPage(Model model) {
        try {
            model.addAttribute("colors", colorService.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List color", "color/list", this.admin_layout);
    }
//goi tren page color add
    @GetMapping(path = "/create")
    public String Save(Model model){
        try{
            model.addAttribute("colors", new ColorDto());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return view(model, "Create Color", "color/add", this.admin_layout);
    }

    // Truyen du lieu len server va check code color
    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("colors") ColorDto colorDto, BindingResult bindingResult){

        ColorDto colorDto_ = colorService.getColorCode(colorDto.getColorCode());
        if(colorDto_ != null){
            bindingResult.rejectValue("colorCode", "", "Color code has taken !");
        }

        ColorDto colorDto__ = colorService.getName(colorDto.getName());
        if(colorDto__ != null) {
            bindingResult.rejectValue("Name", "", "Color Name has taken !");
        }

        try{

        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "redirect:/admin/color?page=0&limit=8";
    }

    @GetMapping(path = "/update/{slug}")
    public String Update(Model model, @PathVariable String slug){
        try{
            model.addAttribute("color_edit", colorService.getBySlugName(slug));

        }catch (Exception e){
            log.info((e.getMessage()));
        }
        return null;
    }


}
