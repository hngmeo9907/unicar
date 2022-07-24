package com.unicar.repos;

import com.unicar.entities.ProductPost;
import com.unicar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPost, Integer> {
}
