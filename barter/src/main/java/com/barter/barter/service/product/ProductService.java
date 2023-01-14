package com.barter.barter.service.product;

import com.barter.barter.data.ProductCategory;
import com.barter.barter.data.dto.product.ProductCreateRequestDto;
import com.barter.barter.data.dto.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    // 전체조회
    List<ProductResponseDto> serachAll();

    // 디테일
    ProductResponseDto detailProduct(Long id);

    // 물건등록
    Long createProduct(ProductCreateRequestDto requestDto);

    // 물건찾기(상품 카테고리)
    List<ProductResponseDto> searchProductByCategory(ProductCategory productCategory);

    // 물건찾기(이름 검색)
    List<ProductResponseDto> searchProudctByTitle(String title);

    // 물건찾기(유저)
    List<ProductResponseDto> searchProductByUserId(String id);

    // 물건삭제(삭제)
    boolean deleteProduct(Long id);
}
