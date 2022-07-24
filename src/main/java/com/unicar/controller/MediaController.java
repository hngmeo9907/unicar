package com.unicar.controller;

import com.unicar.dto.CreateMediaRequest;
import com.unicar.dto.UpdateMediaRequest;
import com.unicar.entities.ProductMedia;
import com.unicar.enums.CarType;
import com.unicar.repos.ProductMediaRepository;
import com.unicar.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private MediaService mediaSv;

    @Autowired
    private ProductMediaRepository mediaRepo;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("county", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType.COUNTY.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType.COUNTY.getType()));
        model.addAttribute("solati", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType.SOLATI.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType.SOLATI.getType()));
        model.addAttribute("fordtransit", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType.FORD_TRANSIT.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType.FORD_TRANSIT.getType()));
        model.addAttribute("fuso", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType.FUSO.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType.FUSO.getType()));
        model.addAttribute("vip24buong", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType._24_BUONG_VIP.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType._24_BUONG_VIP.getType()));
        model.addAttribute("vip34phong", CollectionUtils.isEmpty(mediaSv.getAllMediaByCarType(CarType._34_PHONG_VIP.getType())) ? new ArrayList<>() : mediaSv.getAllMediaByCarType(CarType._34_PHONG_VIP.getType()));
        model.addAttribute("banner", CollectionUtils.isEmpty(mediaSv.getAllBanner()) ? new ArrayList<>() : mediaSv.getAllBanner());
        model.addAttribute("video", CollectionUtils.isEmpty(mediaSv.getAllVideo()) ? new ArrayList<>() : mediaSv.getAllVideo());
        model.addAttribute("logo", mediaSv.getLogo());
        model.addAttribute("mainImage", mediaSv.getMainImage());
        return "media-index";
    }

//    @PostMapping("/addmedia")
//    public String addMedia(@Validated ProductMedia productMedia, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-media";
//        }
//
//        mediaRepo.save(productMedia);
//        return "redirect:/media-index";
//    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        ProductMedia productMedia = mediaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        mediaRepo.delete(productMedia);
        return "redirect:/index";
    }

    @GetMapping("/list-by-type/{id}")
    public String getListMediaByCategory(@PathVariable(name = "id") Integer id, Model model) {
        List<ProductMedia> pms = mediaSv.getAllMediaByCarType(id);
        model.addAttribute("listMedia", pms);
        return "redirect:/";
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateMediaRequest request) {
        mediaSv.create(request);
        return "redirect:/";
    }

    @PutMapping("/update")
    public String edit(@RequestBody UpdateMediaRequest request) {
        mediaSv.update(request);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        mediaSv.delete(id);
        return "redirect:/";
    }
}
