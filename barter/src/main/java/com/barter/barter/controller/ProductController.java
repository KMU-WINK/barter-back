package com.barter.barter.controller;

import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.dto.product.ProductCreateRequestDto;
import com.barter.barter.data.dto.product.ProductResponseDto;
import com.barter.barter.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // 전체조회
    @GetMapping
    List<ProductResponseDto> getAll() {
        return productService.serachAll();
    }

    @GetMapping("/{productId}")
    ProductResponseDto detail(@PathVariable Long productId){
        return productService.detailProduct(productId);
    }

    @PostMapping
    Long createProduct(ProductCreateRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @GetMapping("/search")
    List<ProductResponseDto> searchALlByCategory(@RequestParam(required = false) ProductCategory productCategory,
                                                 @RequestParam(required = false) String title,
                                                 @RequestParam(required = false) String userId) {
        if(title == null && userId == null ) {
            return productService.searchProductByCategory(productCategory);
        }else if ( productCategory == null && userId == null) {
            return productService.searchProudctByTitle(title);
        }else {
            return productService.searchProductByUserId(userId);
        }
    }

    @GetMapping("/search/category")
    List<ProductResponseDto> searchAllByContainTitle(@RequestParam String title) {
        return productService.searchProudctByTitle(title);
    }

    @DeleteMapping("/{product_id}")
    boolean deleteProduct(@PathVariable Long product_id) {
        return productService.deleteProduct(product_id);
    }



}
