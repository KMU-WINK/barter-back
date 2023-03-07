package com.barter.barter.service;

import com.barter.barter.data.dto.product.ProductImageDto;
import com.barter.barter.data.entity.ProductEntity;
import com.barter.barter.data.entity.ProductImageEntity;
import com.barter.barter.data.repository.ProductImageRepository;
import com.barter.barter.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    public List<ProductImageDto> searchAll(Long id){
        return productImageRepository.findByProduct_Id(id).stream().map(ProductImageEntity::toDto).collect(Collectors.toList());
    }

    public void deleteImg(Long id){
        productImageRepository.deleteById(id);
        // 이렇게만 해도 product에 자동 적용이 되나? 확인 필요
    }

    public ProductImageDto postImg(Long product_id, String src){
        ProductEntity productEntity = productRepository.findById(product_id).get();
        ProductImageEntity productImage = ProductImageEntity.builder().src(src).product(productEntity).build();
        productImageRepository.save(productImage);
        List<ProductImageEntity> imageList = productImageRepository.findByProduct_Id(product_id).stream().toList();
        productEntity.setProductImageList(imageList);
        productRepository.save(productEntity);
        return productImage.toDto();
    }
}
