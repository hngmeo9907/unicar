package com.unicar.controller;

import com.unicar.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {



    @GetMapping("/trangchu")
    public String homePage() {
        return "index_copy";
    }

    @GetMapping("/menubar")
    public String menuBar() {
        return "index";
    }

    @GetMapping("/modify")
    public String modify() {
        return "modify";
    }
}
