package com.unicar.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_type")
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "updated_date")
    private Date updatedDate;

    public ProductType() {
    }

    public ProductType(Integer id, String name, String description, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedDate = updatedDate;
    }
}
