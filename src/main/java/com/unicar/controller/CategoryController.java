package com.unicar.controller;

import com.unicar.entities.ProductType;
import com.unicar.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categorySv;

    @GetMapping(name = "/list")
    public ModelAndView getListCategory(Model model) {
        List<ProductType> cats = categorySv.getAllCategory();
        model.addAttribute("categories", cats);
        return new ModelAndView("allcategory.html");
    }

    @GetMapping("/add")
    public String showAddForm(ProductType category, Model model) {
        return "add-category";
    }

    @PostMapping("/save")
    public String create(ProductType category, Model model) {
        categorySv.saveOrUpdateCategory(category);
        return "redirect:/";
    }

    @PutMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Integer id) throws Exception {
        if (id != null) {
            ProductType category2 = categorySv.getCategoryById(id);
            model.addAttribute("shop", category2);
        } else {
            model.addAttribute("shop", new ProductType());
        }
        return "add-category";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        categorySv.deleteCategoryById(id);
        return "redirect:/";
    }
}
