package com.unicar.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_post")
@Data
public class ProductPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "updated_date")
    private Date updatedDate;

    public ProductPost() {
    }

    public ProductPost(Integer id, String title, String description, String content, Integer typeId, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.typeId = typeId;
        this.updatedDate = updatedDate;
    }
}
