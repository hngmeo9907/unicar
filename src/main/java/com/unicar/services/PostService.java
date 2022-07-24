package com.unicar.services;

import com.unicar.entities.ProductPost;
import com.unicar.repos.ProductPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private ProductPostRepository postRepo;

    public List<ProductPost> getAllPost() {
        return postRepo.findAll();
    }

    public ProductPost getPostById(Integer id) {
        return postRepo.getById(id);
    }

    public void deletePostById(Integer id) {
        postRepo.deleteById(id);
    }

    public ProductPost saveOrUpdatePost(ProductPost post) {
        if(post.getId() == null) {
            return postRepo.save(post);}
        else {
            Optional<ProductPost> sOptional = postRepo.findById(post.getId());
            if(sOptional != null) {
                ProductPost post2 = sOptional.get();
                post2.setTitle(post.getTitle());
                post2.setDescription(post.getDescription());
                post2.setContent(post.getContent());
                post2.setTypeId(post.getTypeId());
                post2.setUpdatedDate(new Date());
                return post2;
            }
            else {
                post = postRepo.save(post);
                return post;
            }
        }
    }
}
