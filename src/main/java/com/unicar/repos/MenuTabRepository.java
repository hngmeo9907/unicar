package com.unicar.repos;

import com.unicar.entities.MenuTab;
import com.unicar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface MenuTabRepository extends JpaRepository<MenuTab, Integer> {
    List<MenuTab> findAll();
}
