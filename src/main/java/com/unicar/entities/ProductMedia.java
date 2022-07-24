package com.unicar.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_media")
@Data
public class ProductMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "filename")
    private String filename;

    @Column(name = "_order")
    private Integer order;

    @Column(name = "is_banner")
    private Boolean isBanner;

    @Column(name = "is_video")
    private Boolean isVideo;

    @Column(name = "is_logo")
    private Boolean isLogo;

    @Column(name = "is_main_image")
    private Boolean isMainImage;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "updated_date")
    private Date updatedDate;

    public ProductMedia() {
    }

    public ProductMedia(Integer id, String url, String filename, Integer typeId, Date updatedDate) {
        this.id = id;
        this.url = url;
        this.filename = filename;
        this.typeId = typeId;
        this.updatedDate = updatedDate;
    }
}
