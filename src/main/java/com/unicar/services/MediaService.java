package com.unicar.services;

import com.unicar.dto.CreateMediaRequest;
import com.unicar.dto.UpdateMediaRequest;
import com.unicar.entities.ProductMedia;
import com.unicar.repos.ProductMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private ProductMediaRepository mediaRepo;

    public List<ProductMedia> getAllMediaByCarType(Integer categoryId) {
        return !CollectionUtils.isEmpty(mediaRepo.findAllByTypeId(categoryId))
                ? mediaRepo.findAllByTypeId(categoryId)
                : new ArrayList<>();
    }

    public List<ProductMedia> getAllBanner() {
        return !CollectionUtils.isEmpty(mediaRepo.findAllByIsBanner(true))
                ? mediaRepo.findAllByIsBanner(true)
                : new ArrayList<>();
    }

    public List<ProductMedia> getAllVideo() {
        return !CollectionUtils.isEmpty(mediaRepo.findAllByIsVideo(true))
                ? mediaRepo.findAllByIsVideo(true)
                : new ArrayList<>();
    }

    public ProductMedia getLogo() {
        return mediaRepo.findByIsLogo(true);
    }

    public ProductMedia getMainImage() {
        return mediaRepo.findByIsMainImage(true);
    }

    public ProductMedia create(CreateMediaRequest request) {
        ProductMedia pm = new ProductMedia();
        pm.setUrl(request.getUrl());
        pm.setFilename(request.getFileName());
        pm.setOrder(request.getOrder());
        pm.setUpdatedDate(new Date());
        mediaRepo.save(pm);
        return pm;
    }

    public ProductMedia update(UpdateMediaRequest request) {
        ProductMedia pm = mediaRepo.getById(request.getMediaId());
        pm.setUpdatedDate(new Date());
        pm.setUrl(request.getNewUrl());
        pm.setFilename(request.getFileName());
        pm.setOrder(request.getOrder());
        mediaRepo.save(pm);
        return pm;
    }

    public boolean delete(Integer mediaId) {
        ProductMedia pm = mediaRepo.getById(mediaId);
        mediaRepo.delete(pm);
        return true;
    }

    @Autowired
    private ProductMediaRepository productMediaRepo;

    public List<ProductMedia> getAllMedia() {
        return !CollectionUtils.isEmpty(productMediaRepo.findAll()) ? productMediaRepo.findAll() : new ArrayList<>();
    }

    public ProductMedia getMediaById(Integer categoryId) {
        return productMediaRepo.getById(categoryId);
    }

    public void deleteMediaById(Integer id) {
        productMediaRepo.deleteById(id);
    }

    public ProductMedia saveOrUpdateCategory(ProductMedia media) {
        if(media.getId() == null) {
            return productMediaRepo.save(media);}
        else {
            Optional<ProductMedia> sOptional = productMediaRepo.findById(media.getId());
            if(sOptional!=null) {
                ProductMedia media2 = sOptional.get();
                media2.setFilename(media.getFilename());
                media2.setUrl(media.getUrl());
                media2.setTypeId(media.getTypeId());
                media2.setOrder(media.getOrder());
                media2.setUpdatedDate(new Date());
                return media2;
            }
            else {
                media = productMediaRepo.save(media);
                return media;

            }
        }
    }
}
