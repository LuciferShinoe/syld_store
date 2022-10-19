package com.syld.store.controller.admin;

import com.syld.store.controller.BaseController;
import com.syld.store.dto.ColorDto;
import com.syld.store.services.color.ColorService;
import com.syld.store.ultis.SlugGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return view(model, "Create_Colors", "color/add", this.admin_layout);
    }

    // Truyen du lieu len server va check code color
    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("colors") ColorDto colorDto, BindingResult bindingResult){

        ColorDto colorDto_ = colorService.getColorCode(colorDto.getColor_code());
        if(colorDto_ != null){
            bindingResult.rejectValue("colorCode", "", "Color code has taken !");
        }

        ColorDto colorDto__ = colorService.getByColor_name(SlugGenerator.toSlug(colorDto.getColor_name()));
        if(colorDto__ != null) {
            bindingResult.rejectValue("color_name", "", "Color name has taken !");
        }

        try{
            colorService.save(colorDto);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "redirect:/admin/color?page=0&limit=8";
    }

    @GetMapping(path = "/update/{color_name}")
    public String Update(Model model, @PathVariable String color_name){
        try{
            model.addAttribute("color_edit", colorService);

        }catch (Exception e){
            log.info((e.getMessage()));
        }
        return null;
    }
    @PostMapping(path = "/update")
    public String Update(@Valid @ModelAttribute("color_edit") ColorDto colorDto, BindingResult bindingResult, Model model) {
        ColorDto colorDto_ = colorService.getByNameNotSame(colorDto.getColor_name(), colorDto.getId());
        if(colorDto_ != null){
            bindingResult.rejectValue("color_name", "", "Color Name has taken !");
        }
        ColorDto colorDto__ = colorService.getColorCode(colorDto.getColor_code());
        if(colorDto__ != null){
            bindingResult.rejectValue("color_code", "", "Color code has taken !");
        }
        if(bindingResult.hasErrors()) {
            return view(model, "Edit - Color", "color/edit", this.admin_layout);
        }
        try {
            colorService.update(colorDto);
        }catch (Exception e) {
            log.info((e.getMessage()));
        }
        return "redirct:/admin/colors/";
    }

    @GetMapping(path = "remove/{id}")
    public String Remove(RedirectAttributes redirectAttributes, @PathVariable String id) {
        try {
            colorService.remove(id);
            redirectAttributes.addAttribute("message", "Succes");
        }catch (Exception e) {
            redirectAttributes.addAttribute("message", "Falied");
        }
        return "redirect:/admin/colors";
    }
}
