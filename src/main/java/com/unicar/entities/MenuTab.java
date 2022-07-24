package com.unicar.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "menu_tabs")
@Data
public class MenuTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "updated_date")
    private Date updatedDate;

    public MenuTab() {
    }

    public MenuTab(Integer id, String name, String description, String content, Date createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.content = content;
        this.updatedDate = updatedDate;
    }
}
