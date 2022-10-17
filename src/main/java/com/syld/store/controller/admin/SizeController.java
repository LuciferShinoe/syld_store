package com.syld.store.controller.admin;

import com.sun.jdi.event.ExceptionEvent;
import com.syld.store.controller.BaseController;
import com.syld.store.dto.SizeDto;
import com.syld.store.interfaces.controllers.ICrudController;
import com.syld.store.interfaces.services.ICrudService;
import com.syld.store.services.size.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/sizes")
@Slf4j
@RequiredArgsConstructor
public class SizeController extends BaseController {

    private final SizeService sizeService;

    @GetMapping(path = "/{id}")
    public String SizeDetail(Model model, @PathVariable String id){
        try {
            model.addAttribute("size_detail", sizeService.getById(id));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List - Size", "size/list", this.admin_layout);
    }
    @GetMapping
    public String GetAll(Model model) {
        try {
            model.addAttribute("list", sizeService.getAll());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return view(model, "List - Size", "size/list", this.admin_layout);
    }

    @GetMapping(path = "/create")
    public String Save(Model model) {
        model.addAttribute("size", new SizeDto());
        return view(model, "Create - Size", "size/add", this.admin_layout);
    }

    @PostMapping(path = "/create")
    public String Save(@Valid @ModelAttribute("size") SizeDto sizeDto, BindingResult bindingResult, Model model) {

        SizeDto sizeDto_ = sizeService.getByName(sizeDto.getSize_name().toUpperCase());
        if (sizeDto_ != null) {
            bindingResult.rejectValue("size_name", "", "Size name has taken!");
        }

        if (bindingResult.hasErrors()) {
            return view(model, "Create - Size", "size/add", this.admin_layout);
        }
        try {
            sizeService.save(sizeDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/sizes/";
    }

    @GetMapping(path = "/update/{id}")
    public String Update(Model model, @PathVariable String id) {
        try {
            model.addAttribute("size_edit", sizeService.getById(id));
            return view(model, "Edit - Size", "size/edit", this.admin_layout);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/sizes/";
    }

    @PostMapping(path = "/update")
    public String Update(@Valid @ModelAttribute("size_edit") SizeDto sizeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return view(model, "Edit - Size", "size/edit", this.admin_layout);
        }
        try {
            sizeService.update(sizeDto);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/sizes";
    }

    @GetMapping("/remove/{id}")
    public String Remove(@PathVariable String id) {
        try {
            sizeService.remove(id);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "redirect:/admin/sizes";
    }
}
