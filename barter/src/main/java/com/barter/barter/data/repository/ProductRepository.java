package com.barter.barter.data.repository;

import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.entity.ProductEntity;
import com.barter.barter.data.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findAllByProductCategory(ProductCategory productCategory);
    List<ProductEntity> findAllByTitleContains(String name);
    List<ProductEntity> findAllByUser(UserEntity userEntity);


}
