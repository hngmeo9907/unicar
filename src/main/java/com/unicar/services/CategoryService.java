package com.unicar.services;

import com.unicar.entities.ProductType;
import com.unicar.repos.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepo;

    public List<ProductType> getAllCategory() {
        return !CollectionUtils.isEmpty(productCategoryRepo.findAll()) ? productCategoryRepo.findAll() : new ArrayList<>();
    }

    public ProductType getCategoryById(Integer categoryId) {
        return productCategoryRepo.getById(categoryId);
    }

    public void deleteCategoryById(Integer id) {
        productCategoryRepo.deleteById(id);
    }

    public ProductType saveOrUpdateCategory(ProductType category) {
        if(category.getId() == null) {
            return productCategoryRepo.save(category);}
        else {
            Optional<ProductType> sOptional = productCategoryRepo.findById(category.getId());
            if(sOptional!=null) {
                ProductType category2 = sOptional.get();
                category2.setName(category.getName());
                category2.setDescription(category.getDescription());
                category2.setUpdatedDate(category.getUpdatedDate());
                return category2;
            }
            else {
                category = productCategoryRepo.save(category);
                return category;

            }
        }
    }
}
