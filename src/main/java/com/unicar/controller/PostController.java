package com.unicar.controller;

import com.unicar.entities.ProductPost;
import com.unicar.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postSv;

    @GetMapping(name = "/all")
    public String allCategory(Model model) {
        List<ProductPost> posts = postSv.getAllPost();
        model.addAttribute("posts", posts);
        return "allpost";
    }

    @GetMapping("/add")
    public String showAddForm(ProductPost post, Model model) {
        return "add-post";
    }

    @PostMapping("/save")
    public String create(ProductPost post, Model model) {
        postSv.saveOrUpdatePost(post);
        return "redirect:/";
    }

    @PutMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Integer id) throws Exception {
        if (id != null) {
            ProductPost post2 = postSv.getPostById(id);
            model.addAttribute("shop", post2);
        } else {
            model.addAttribute("shop", new ProductPost());
        }
        return "add-post";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Integer id) {
        postSv.deletePostById(id);
        return "redirect:/";
    }
}
