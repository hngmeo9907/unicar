package com.unicar.services;

import com.unicar.entities.MenuTab;
import com.unicar.repos.MenuTabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    private MenuTabRepository menuTabRepo;

    public List<MenuTab> getMenuTabBar() {
        return menuTabRepo.findAll();
    }
}
