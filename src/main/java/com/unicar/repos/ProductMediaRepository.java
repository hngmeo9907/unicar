package com.unicar.repos;

import com.unicar.entities.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMediaRepository extends JpaRepository<ProductMedia, Integer> {
    List<ProductMedia> findAllByTypeId(Integer typeId);

    List<ProductMedia> findAllByIsBanner(Boolean isBanner);

    List<ProductMedia> findAllByIsVideo(Boolean isVideo);

    ProductMedia findByIsLogo(Boolean isLogo);

    ProductMedia findByIsMainImage(Boolean isMain);

    List<ProductMedia> findAll();
}
